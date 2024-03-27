package com.happiness.githerbs.domain.search.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.Data;

@FeignClient(name = "fastApi", url = "http://127.0.0.1:8000/v1/m1")
public interface FastApiClient {

	@GetMapping("search")
	keywordListResponseDto getKeyword(@RequestParam("herbId") Integer herbId);

	@Data
	class keywordListResponseDto {
		List<Integer> herbIds;
	}
}
