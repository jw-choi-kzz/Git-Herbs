package com.happiness.githerbs.domain.herb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping("/{herbId}/bookmark")
	public ResponseEntity<SuccessResponse<?>> getBookmark(
			@RequestHeader(required = false) String authorization, @PathVariable Integer herbId) {
		int userId = 0;
		if(authorization != null) userId = jwtService.validateToken(authorization).getMemberId();
		return ResponseEntity.ok(
				new SuccessResponse<>(HttpStatus.OK.value(), bookmarkService.getBookmark(userId, herbId)));
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
