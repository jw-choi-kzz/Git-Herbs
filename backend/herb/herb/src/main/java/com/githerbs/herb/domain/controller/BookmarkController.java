package com.githerbs.herb.domain.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.githerbs.herb.domain.service.BookmarkService;
import com.githerbs.herb.global.common.response.BaseResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/herbs")
public class BookmarkController {

	private final BookmarkService bookmarkService;

	@PostMapping("/{herbId}/bookmark")
	public ResponseEntity<BaseResponse<?>> addBookmark(@PathVariable Integer herbId) {
		// (@AuthenticationPrincipal User user, @PathVariable Integer herbId)
		Integer userId = 1;
		bookmarkService.addBookmark(userId, herbId);
		return ResponseEntity.ok(
			new BaseResponse<>(HttpStatus.OK.value(), null));
	}

	@DeleteMapping("/{herbId}/bookmark")
	public ResponseEntity<BaseResponse<?>> deleteBookmark(@PathVariable Integer herbId) {
		// (@AuthenticationPrincipal User user, @PathVariable Integer herbId)
		Integer userId = 1;
		bookmarkService.deleteBookmark(userId, herbId);
		return ResponseEntity.ok(
			new BaseResponse<>(HttpStatus.OK.value(), null));
	}
}
