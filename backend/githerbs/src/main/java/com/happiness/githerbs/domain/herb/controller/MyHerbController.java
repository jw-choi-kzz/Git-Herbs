package com.happiness.githerbs.domain.herb.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.happiness.githerbs.domain.auth.service.JwtService;
import com.happiness.githerbs.domain.herb.dto.request.MyHerbRequestDto;
import com.happiness.githerbs.domain.herb.service.MyHerbService;
import com.happiness.githerbs.global.common.response.SuccessResponse;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/herbs")
@RequiredArgsConstructor
public class MyHerbController {

	private final MyHerbService myHerbService;
	private final JwtService jwtService;
	@GetMapping("/{herbId}/my-herbs")
	public ResponseEntity<SuccessResponse<?>> getMyHerbList(
		@RequestHeader String authorization, @PathVariable Integer herbId, Pageable pageable) {
		int userId = jwtService.validateToken(authorization).getMemberId();
		return ResponseEntity.ok(new SuccessResponse<>(HttpStatus.OK.value(),
			myHerbService.getMyHerbList(userId, herbId, pageable)));

	}

	/* 내 도감 사진에 사진 등록 */
	@PostMapping("/my-herbs")
	public ResponseEntity<SuccessResponse<?>> addMyHerb(
		@RequestHeader String authorization, @RequestBody MyHerbRequestDto myHerbRequestDto) {

		int userId = jwtService.validateToken(authorization).getMemberId();
		myHerbService.addMyHerb(userId, myHerbRequestDto);
		return ResponseEntity.ok(new SuccessResponse<>(HttpStatus.OK.value(), null));
	}

	/* 내 도감 사진에서 사진 삭제 */
	@PatchMapping("/my-herbs")
	public ResponseEntity<SuccessResponse<?>> deleteHerb(
		@RequestHeader String authorization, @PathParam("myHerbId") Integer myHerbId) {

		int userId = jwtService.validateToken(authorization).getMemberId();
		myHerbService.deleteMyHerb(myHerbId);
		return ResponseEntity.ok(new SuccessResponse<>(HttpStatus.OK.value(), null));
	}
}
