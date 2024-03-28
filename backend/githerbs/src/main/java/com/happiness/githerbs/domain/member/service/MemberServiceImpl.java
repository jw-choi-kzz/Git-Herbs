package com.happiness.githerbs.domain.member.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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

	@Override
	public String loginService() {
		return null;
	}
}
