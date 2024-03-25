package com.happiness.githerbs.domain.manual.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@FeignClient(name = "kakaoLocal", url = "https://dapi.kakao.com")
public interface KakaoLocalClient {
	@GetMapping("/v2/local/geo/coord2address.JSON")
	KakaoLocalResponseDto getAddress(@RequestHeader("Authorization") String kakaoApiKey,
		@RequestParam("x") String lng,
		@RequestParam("y") String lat);

	@Data
	class KakaoLocalResponseDto {
		private Meta meta;
		private List<Document> documents;

		@Data
		static class Meta {
			@JsonProperty("total_count")
			private int totalCount;
		}

		@Data
		static class Document {
			@JsonProperty("road_address")
			private Address roadAddress;
			private Address address;
		}

		@Data
		static class Address {
			@JsonProperty("region_1depth_name")
			private String region1depthName;
			@JsonProperty("region_2depth_name")
			private String region2depthName;
		}
	}

}
