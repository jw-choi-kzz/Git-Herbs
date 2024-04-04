package com.happiness.githerbs.domain.member.service;

import java.net.URI;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import feign.Response;

@FeignClient(name = "KakaoProfileClient", url = "http://localhost")
public interface KakaoProfileClient {
	@GetMapping
	Response userProfileClient(URI uri);
}
