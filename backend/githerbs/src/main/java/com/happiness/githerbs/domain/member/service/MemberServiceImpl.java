package com.happiness.githerbs.domain.member.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.happiness.githerbs.domain.member.dto.request.KakaoAuthorizeParameterDto;
import com.happiness.githerbs.domain.member.dto.request.KakaoTokenRequestDto;
import com.happiness.githerbs.domain.member.dto.response.UserTokenResponseDto;
import com.happiness.githerbs.domain.member.entity.StateRedisEntity;
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


	private final StateRedisRepository redis;
	private final KakaoFeignClient kakao;


	/**
	 * 카카오 로그인을 위한 주소 반환
	 * */
	@Override
	public String loginService() {
		// state 고유 값 생성
		var state = UUID.randomUUID().toString().replaceAll("-" , "");

		// state 저장
		var entity = StateRedisEntity.builder().state(state).build();
		redis.save(entity);
		redirectUri = "http://localhost:8080/v1/user/token";
		var sb = new StringBuilder(authorizeUrl);
		sb.append("?client_id=").append(clientId).append("&redirect_uri=").append(redirectUri).append("&response_type=code").append("&state=").append(state);
		//.append("&scope=profile_nickname,profile_image,openid")
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

		// TODO : 카카오 access token 발급
		var tokenRequest = KakaoTokenRequestDto.builder().clientId(clientId).code(dto.code()).redirectUri(redirectUri).build();
		var token = kakao.tokenClient(tokenRequest);

		// TODO : access token으로 카카오 회원번호 조회

		// TODO : 카카오 회원번호로 회원 조회(없으면 회원가입)

		// TODO : 회원 조회 후 회원 정보로 jwt 발급

		// TODO : 회원번호, device-id를 이용해 카카오 access token, refresh token, ID token을 redis 저장

		// TODO : jwt와 회원정보 반환
		return null;
	}
}
