package com.happiness.githerbs.domain.search.service;

import java.io.File;
import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.happiness.githerbs.domain.search.dto.response.HerbSimilarityResponseDto;

import lombok.Data;

@FeignClient(name = "fastApi", url = "${feign.fast-api.url}")
public interface FastApiClient {

	@GetMapping("search")
	KeywordListResponseDto getKeyword(@RequestParam("herbId") Integer herbId);

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	Map<String, Double> getSimilarityClient(URI uri,  @RequestPart("img") MultipartFile img);

	@Data
	class KeywordListResponseDto {
		List<Integer> herbIds;
	}
}
