package com.happiness.githerbs.domain.search.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.Data;

@FeignClient(name = "fastApi", url = "${feign.fast-api.url}")
public interface FastApiClient {

	@GetMapping("search")
	KeywordListResponseDto getKeyword(@RequestParam("herbId") Integer herbId);

	@Data
	class KeywordListResponseDto {
		List<Integer> herbIds;
	}
}
