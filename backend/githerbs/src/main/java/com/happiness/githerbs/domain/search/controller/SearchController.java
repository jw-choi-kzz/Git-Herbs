package com.happiness.githerbs.domain.search.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.happiness.githerbs.domain.search.dto.response.SearchResponseDto;
import com.happiness.githerbs.domain.search.service.SearchService;
import com.happiness.githerbs.global.common.response.SuccessResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class SearchController {

	private final SearchService searchService;

	@GetMapping("/search")
	public ResponseEntity<SuccessResponse<List<SearchResponseDto>>> search(@RequestParam String keyword) {
		return ResponseEntity.ok(new SuccessResponse<>(HttpStatus.OK, searchService.search(keyword)));
	}

}
