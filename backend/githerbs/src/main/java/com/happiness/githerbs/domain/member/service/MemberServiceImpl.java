package com.happiness.githerbs.domain.member.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.happiness.githerbs.domain.member.dto.common.IdTokenPayload;
import com.happiness.githerbs.domain.member.dto.request.KakaoAuthorizeParameterDto;
import com.happiness.githerbs.domain.member.dto.request.KakaoTokenRequestDto;
import com.happiness.githerbs.domain.member.dto.request.KakaoUserInfoRequestDto;
import com.happiness.githerbs.domain.member.dto.response.UserTokenResponseDto;
import com.happiness.githerbs.domain.member.entity.Member;
import com.happiness.githerbs.domain.member.entity.StateRedisEntity;
import com.happiness.githerbs.domain.member.repository.MemberRepository;
import com.happiness.githerbs.domain.member.repository.StateRedisRepository;
import com.happiness.githerbs.global.common.code.ErrorCode;
import com.happiness.githerbs.global.common.exception.BaseException;

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

	private final StateRedisRepository redis;
	private final KakaoOAuthClient oAuthClient;
	private final KakaoUserClient userClient;
	private final MemberRepository repo;


	/**
	 * 카카오 로그인을 위한 주소 반환
	 * */
	@Override
	@Transactional
	public String loginService() {
		// state 고유 값 생성
		var state = UUID.randomUUID().toString().replaceAll("-", "");

		// state 저장
		var entity = StateRedisEntity.builder().state(state).build();
		var result = redis.save(entity);
		if(!result.getState().equals(state)) {
			throw new BaseException("redis save error", ErrorCode.INTERNAL_SERVER_ERROR);
		}
		redirectUri = "http://localhost:8080/v1/user/token";
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
	public UserTokenResponseDto tokenService(KakaoAuthorizeParameterDto dto) {
		// state 확인
		var entity = redis.findById(dto.state()).orElseThrow(() -> new BaseException(ErrorCode.USER_INVALID_STATE));
		redis.delete(entity);
		// 카카오 access token 발급
		var tokenRequest = KakaoTokenRequestDto.builder().clientId(clientId).code(dto.code()).redirectUri(redirectUri).build();
		var token = oAuthClient.tokenClient(tokenRequest);
		//log.info("token : {}", token);
		// id token으로 카카오 회원번호 조회
		var idToken = token.idToken();
		var payload = decodeIdToken(idToken);
		if(payload == null || !payload.iss().equalsIgnoreCase(tokenUrl) || !payload.aud().equals(clientId)) throw new BaseException(ErrorCode.USER_KAKAO_FAIL);
		var kakaoId = Long.parseLong(payload.sub());
		// TODO : 카카오 회원번호로 회원 조회(없으면 회원가입)
		var member = repo.findByKakaoIdAndDeleted(kakaoId, false).orElse(null);
		if(member == null) member = registService(token.accessToken());

		// TODO : 회원 조회 후 회원 정보로 jwt 발급

		// TODO : 회원번호, device-id를 이용해 카카오 access token, refresh token, ID token을 redis 저장

		// TODO : jwt와 회원정보 반환
		return null;
	}

	/**
	 * 회원가입 수행
	 * */
	@Override
	public Member registService(String accessToken) {
		// TODO : get kakao user id, nickname, profile image using feign client
		var param = KakaoUserInfoRequestDto.builder().secureResource(true).build();
		var profile = userClient.userInfoClient("Bearer " + accessToken, param);
		//log.info("profile : {}", profile);
		// TODO : save profile image to S3
		// TODO : if profile image is default_profile, don't save to S3
		var s3Url = "";

		// TODO : save member info to mysql

		// TODO : get member info from mysql

		// TODO : return member info
		return null;
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
}
