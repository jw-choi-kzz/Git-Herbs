package com.happiness.githerbs.domain.search.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.happiness.githerbs.domain.auth.service.JwtService;
import com.happiness.githerbs.domain.search.dto.response.SearchResponseDto;
import com.happiness.githerbs.domain.search.service.SearchService;
import com.happiness.githerbs.global.common.response.SuccessResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class SearchController {

	private final SearchService searchService;
	private final JwtService jwtService;

	@GetMapping("/search")
	public ResponseEntity<SuccessResponse<List<SearchResponseDto>>> search(@RequestParam String keyword,
		@RequestHeader(required = false) String authorization) {
		Integer memberId = null;
		if (authorization != null) {
			memberId = jwtService.validateToken(authorization).getMemberId();
		}
		return ResponseEntity.ok(new SuccessResponse<>(HttpStatus.OK, searchService.search(memberId, keyword)));
	}

	@GetMapping("/search/recent")
	public ResponseEntity<SuccessResponse<List<String>>> findRecent(@RequestHeader String authorization) {
		int memberId = jwtService.validateToken(authorization).getMemberId();
		return ResponseEntity.ok(new SuccessResponse<>(HttpStatus.OK, searchService.findRecent(memberId)));
	}

}
