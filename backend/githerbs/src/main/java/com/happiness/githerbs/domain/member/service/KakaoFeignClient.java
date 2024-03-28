package com.happiness.githerbs.domain.member.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.happiness.githerbs.domain.member.dto.request.KakaoTokenRequestDto;
import com.happiness.githerbs.domain.member.dto.response.KaKaoTokenResponse;

@FeignClient(name = "KakaoLoginClient", url = "https://")
public interface KakaoFeignClient {
	@PostMapping("kauth.kakao.com/oauth/token")
	public KaKaoTokenResponse tokenClient(@RequestBody KakaoTokenRequestDto dto);

	@PostMapping("kapi.kakao.com/v1/user/logout")
	public Integer logoutClient(@RequestHeader("Authorization") String accessToken);
}
