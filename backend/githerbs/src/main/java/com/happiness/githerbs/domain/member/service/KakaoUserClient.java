package com.happiness.githerbs.domain.member.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.happiness.githerbs.domain.member.dto.request.KakaoUserInfoRequestDto;
import com.happiness.githerbs.domain.member.dto.response.KakaoUserInfoResponseDto;

@FeignClient(name = "KakaoUserClient", url ="${feign.kakao.user}" )
public interface KakaoUserClient {
	@PostMapping("/v1/user/logout")
	public Long  logoutClient(@RequestHeader("Authorization") String accessToken);
	@GetMapping(value = "/v2/user/me", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public KakaoUserInfoResponseDto userInfoClient(@RequestHeader("Authorization") String accessToken, KakaoUserInfoRequestDto dto);
}
