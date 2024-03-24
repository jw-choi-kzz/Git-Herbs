package com.happiness.githerbs.domain.herb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.happiness.githerbs.domain.auth.service.JwtService;
import com.happiness.githerbs.domain.herb.service.BookmarkService;
import com.happiness.githerbs.global.common.response.SuccessResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/herbs")
public class BookmarkController {

	private final BookmarkService bookmarkService;
	private final JwtService jwtService;

	@PostMapping("/{herbId}/bookmark")
	public ResponseEntity<SuccessResponse<?>> addBookmark(
		@RequestHeader String authorization, @PathVariable Integer herbId) {

		int userId = jwtService.validateToken(authorization).getMemberId();
		bookmarkService.addBookmark(userId, herbId);
		return ResponseEntity.ok(
			new SuccessResponse<>(HttpStatus.OK.value(), null));
	}

	@DeleteMapping("/{herbId}/bookmark")
	public ResponseEntity<SuccessResponse<?>> deleteBookmark(
		@RequestHeader String authorization, @PathVariable Integer herbId) {

		int userId = jwtService.validateToken(authorization).getMemberId();
		bookmarkService.deleteBookmark(userId, herbId);
		return ResponseEntity.ok(
			new SuccessResponse<>(HttpStatus.OK.value(), null));
	}
}
