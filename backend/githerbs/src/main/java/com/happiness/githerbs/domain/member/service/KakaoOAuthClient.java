package com.happiness.githerbs.domain.member.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.happiness.githerbs.domain.member.dto.request.KakaoTokenRequestDto;
import com.happiness.githerbs.domain.member.dto.response.KaKaoTokenResponseDto;

@FeignClient(name = "KakaoOAuthClient", url ="${feign.kakao.token}" )
public interface KakaoOAuthClient {

	@PostMapping(value = "/oauth/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public KaKaoTokenResponseDto tokenClient(@RequestBody KakaoTokenRequestDto dto);


}
