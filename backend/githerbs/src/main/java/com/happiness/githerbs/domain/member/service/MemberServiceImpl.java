package com.happiness.githerbs.domain.member.service;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.happiness.githerbs.domain.auth.dto.common.AuthorizationTokenDto;
import com.happiness.githerbs.domain.auth.dto.common.JwtScopeDto;
import com.happiness.githerbs.domain.auth.dto.common.MemberInfoDto;
import com.happiness.githerbs.domain.auth.service.JwtService;
import com.happiness.githerbs.domain.member.dto.common.IdTokenPayload;
import com.happiness.githerbs.domain.member.dto.request.KakaoAuthorizeParameterDto;
import com.happiness.githerbs.domain.member.dto.request.KakaoTokenRequestDto;
import com.happiness.githerbs.domain.member.dto.request.KakaoUserInfoRequestDto;
import com.happiness.githerbs.domain.member.dto.response.ReissueTokenResponseDto;
import com.happiness.githerbs.domain.member.dto.response.UserInfoResponseDto;
import com.happiness.githerbs.domain.member.dto.response.UserTokenResponseDto;
import com.happiness.githerbs.domain.member.entity.KakaoLoginRedisEntity;
import com.happiness.githerbs.domain.member.entity.Member;
import com.happiness.githerbs.domain.member.entity.StateRedisEntity;
import com.happiness.githerbs.domain.member.repository.KakaoLoginRedisRepository;
import com.happiness.githerbs.domain.member.repository.MemberRepository;
import com.happiness.githerbs.domain.member.repository.StateRedisRepository;
import com.happiness.githerbs.global.common.code.ErrorCode;
import com.happiness.githerbs.global.common.exception.BaseException;
import com.happiness.githerbs.global.config.S3Uploader;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl  implements MemberService {

	@Value("${kakao.login.secret}")
	private String clientId;
	@Value("${kakao.login.redirect}")
	private String redirectUri;
	@Value("${feign.kakao.authorize}")
	private String authorizeUrl;
	@Value("${feign.kakao.token}")
	private String tokenUrl;
	// TODO : member 완성하면 /tmp로 바꾸기
	@Value("${kakao.login.tmp-path}")
	private String tmpPath;

	private final StateRedisRepository stateRedis;
	private final KakaoOAuthClient oAuthClient;
	private final KakaoUserClient userClient;
	private final MemberRepository repo;
	private final KakaoProfileClient profileClient;
	private final S3Uploader s3;
	private final JwtService jwt;
	private final KakaoLoginRedisRepository loginRedis;

	/**
	 * 카카오 로그인을 위한 주소 반환
	 * */
	@Override
	@Transactional
	public String loginService(String redirect) {
		// state 고유 값 생성
		var state = UUID.randomUUID().toString().replaceAll("-", "");

		// state 저장
		if(redirect ==null) redirect = "";
		var entity = StateRedisEntity.builder().state(state).redirectUrl(redirect).build();
		var result = stateRedis.save(entity);
		if(!result.getState().equals(state)) {
			throw new BaseException("redis save error", ErrorCode.INTERNAL_SERVER_ERROR);
		}
		var sb = new StringBuilder(authorizeUrl);
		sb.append("?client_id=")
			.append(clientId)
			.append("&redirect_uri=")
			.append(redirectUri)
			.append("&response_type=code")
			.append("&state=")
			.append(state);
		return sb.toString();
	}


	/**
	 * 카카오 인가 코드로 access token 발급
	 * */
	@Override
	@Transactional
	public UserTokenResponseDto tokenService(KakaoAuthorizeParameterDto dto) {
		// state 확인
		var entity = stateRedis.findById(dto.state()).orElseThrow(() -> new BaseException(ErrorCode.USER_INVALID_STATE));
		var redirect = entity.getRedirectUrl();
		stateRedis.delete(entity);
		// 카카오 access token 발급
		var tokenRequest = KakaoTokenRequestDto.builder().clientId(clientId).code(dto.code()).redirectUri(redirectUri).build();
		var token = oAuthClient.tokenClient(tokenRequest);
		// id token으로 카카오 회원번호 조회
		var idToken = token.idToken();
		var payload = decodeIdToken(idToken);
		if(payload == null || !payload.iss().equalsIgnoreCase(tokenUrl) || !payload.aud().equals(clientId)) throw new BaseException(ErrorCode.USER_KAKAO_FAIL);
		var kakaoId = Long.parseLong(payload.sub());
		// 카카오 회원번호로 회원 조회(없으면 회원가입)
		var member = repo.findByKakaoIdAndDeleted(kakaoId, false).orElse(null);
		if(member == null) member = registService(token.accessToken());
		// 회원 조회 후 회원 정보로 jwt 발급
		var memberInfo = MemberInfoDto.builder().memberId(member.getId()).memberNickname(member.getNickname()).scope(
			JwtScopeDto.MEMBER).build();
		var state = UUID.randomUUID().toString().replaceAll("-", "");
		var jwtToken = jwt.createToken(null, memberInfo, state);
		if(!state.equals(jwtToken.getState())) throw new BaseException("토큰 에러 문의",ErrorCode.INTERNAL_SERVER_ERROR);
		// 회원번호, device-id를 이용해 카카오 access token, refresh token, ID token을 redis 저장
		var loginEntity = KakaoLoginRedisEntity.builder().id(member.getId()+":"+jwtToken.getDeviceId()).kakaoAccessToken(token.accessToken()).kakaoIdToken(idToken).kakaoRefreshToken(token.refreshToken()).build();
		loginRedis.save(loginEntity);
		// jwt와 회원정보 반환
		return UserTokenResponseDto.builder().accessToken(jwtToken.getAccessToken()).refreshToken(
			jwtToken.getRefreshToken()).deviceId(jwtToken.getDeviceId()).userId(member.getId()).nickname(
			member.getNickname()).imgId(member.getImgId()).redirectUri(redirect).build();
	}

	/**
	 * 회원가입 수행
	 * */
	@Override
	@Transactional
	public Member registService(String accessToken) {
		// get kakao user id, nickname, profile image using feign client
		var param = KakaoUserInfoRequestDto.builder().secureResource(true).build();
		var profile = userClient.userInfoClient("Bearer " + accessToken, param);
		// save profile image to S3
		var s3Url = "";
		// if profile image is default_profile, don't save to S3
		if(profile.properties() != null && !profile.properties().profileImage().contains("default_profile")) {
			s3Url = uploadProfile(profile.properties().profileImage(), profile.id());
		}
		// save member info to mysql
		var nickname = (profile.properties() == null || profile.properties().nickname() == null || profile.properties().nickname().isBlank()) ? randomNickname() : profile.properties().nickname();
		var member = Member.builder().kakaoId(profile.id()).nickname(nickname).imgId(s3Url).deleted(false).build();
		repo.save(member);
		// get member info from mysql
		var result = repo.findByKakaoIdAndDeleted(profile.id(), false).orElse(null);
		if(result == null) throw new BaseException("로그인 실패", ErrorCode.USER_NOT_FOUND);
		// return member info
		return result;
	}

	@Override
	@Transactional
	public Integer withdrawService(String accessToken, String deviceId) {
		// logout
		var memberId = logoutService(accessToken, deviceId);
		// delete column of member set true
		var member = repo.findById(memberId).orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));
		repo.deleteMember(member.getId());
		return member.getId();
	}

	@Override
	public UserInfoResponseDto getUserService(int userId) {
		var member = repo.findById(userId).orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));
		var imgUrl = member.getImgId();
		return UserInfoResponseDto.builder().id(member.getId()).nickname(member.getNickname()).img(imgUrl).build();
	}

	@Override
	@Transactional
	public Integer logoutService(String accessToken, String deviceId) {
		// validate access token
		var memberInfo = jwt.validateToken(accessToken);
		// revoke access token and refresh token using device id
		var revokeToken = AuthorizationTokenDto.builder().accessToken(accessToken).build();
		var result = jwt.revokeToken(deviceId, revokeToken);
		// logout kakao account using kakao access token
		var kakao = loginRedis.findById(memberInfo.getMemberId() + ":" + deviceId).orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));
		try {
			var kakaoID = userClient.logoutClient("Bearer " + kakao.getKakaoAccessToken());
		} catch (FeignException e) {
			log.error("logout error: {}", e.getMessage());  // 에러가 나도 로그아웃은 진행되어야 함
		}
		// delete kakao tokens in redis
		loginRedis.deleteById(memberInfo.getMemberId() + ":" + deviceId);
		return memberInfo.getMemberId();
	}

	@Override
	public ReissueTokenResponseDto reissueService(String deviceId, AuthorizationTokenDto dto) {
		// reissue access token and refresh token with device id
		var state = UUID.randomUUID().toString().replaceAll("-", "");
		var result = jwt.reissueToken(deviceId, dto, state);
		// return new access token and refresh token
		if(!result.getState().equals(state))  throw new BaseException("state 에러 문의",ErrorCode.USER_INVALID_STATE);
		return ReissueTokenResponseDto.builder().accessToken(result.getAccessToken()).refreshToken(result.getRefreshToken()).deviceId(deviceId).build();
	}

	private String randomNickname() {
		List<String> prefix = Arrays.asList("성실한", "열정적인", "행복한", "평화로운", "자유로운", "창의적인", "활발한", "유쾌한", "친절한", "용감한", "신중한", "정직한", "근면한", "겸손한", "헌신적인", "희망찬", "진취적인", "지혜로운", "신비로운");
		String name = "약초꾼";
		String number = String.valueOf((int)(Math.random() * 99));
		Collections.shuffle(prefix);
		return prefix.get(0) + " " + name + number;
	}

	/**
	 * id token payload 디코딩
	 * */
	@Override
	public IdTokenPayload decodeIdToken(String idToken) {
		var split = idToken.split("\\.");
		var payload = new String(java.util.Base64.getDecoder().decode(split[1]));
		var gson = new Gson();
		return gson.fromJson(payload, IdTokenPayload.class);
	}

	@Override
	public String uploadProfile(String profileUrl, Long kakaoId) {
		URI uri = URI.create(profileUrl);
		try(var res = profileClient.userProfileClient(uri)) {
			if (res.status() >= 400) return "";
			var in = res.body().asInputStream();
			String fileName = profileUrl.split("/")[profileUrl.split("/").length - 2];
			String extension = profileUrl.split("\\.")[profileUrl.split("\\.").length - 1];
			String path = tmpPath + fileName + "." + extension;
			return s3.upload(in, "profile", path);
		} catch (Exception e) {
			log.error("profile error: {}", kakaoId);
			log.error(Arrays.toString(e.getStackTrace()));
			return "";    // s3 업로드에 에러가 발생해도 회원가입은 진행되어야 함
		}
	}

}
