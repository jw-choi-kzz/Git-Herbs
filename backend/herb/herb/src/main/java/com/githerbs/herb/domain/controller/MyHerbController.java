package com.githerbs.herb.domain.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.githerbs.herb.domain.dto.MyHerbRequestDto;
import com.githerbs.herb.domain.service.MyHerbService;
import com.githerbs.herb.global.common.response.BaseResponse;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/herbs")
@RequiredArgsConstructor
public class MyHerbController {

	private final MyHerbService myHerbService;

	@GetMapping("/{herbId}/my-herbs")
	public ResponseEntity<BaseResponse<?>> getMyHerbList(@PathVariable Integer herbId, Pageable pageable) {
		// (@AuthenticationPrincipal User user, @PathVariable Integer herbId, Pageable pageable){
		Integer userId = 1;
		return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(),
			myHerbService.getMyHerbList(userId, herbId, pageable)));

	}

	/* 내 도감 사진에 사진 등록 */
	@PostMapping("/my-herbs")
	public ResponseEntity<BaseResponse<?>> addMyHerb(@RequestBody MyHerbRequestDto myHerbRequestDto) {
		// (@AuthenticationPrincipal User user, @RequestBody MyHerbRequestDto myHerbRequestDto){
		Integer userId = 1;
		myHerbService.addMyHerb(userId, myHerbRequestDto);
		return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), null));
	}

	/* 내 도감 사진에서 사진 삭제 */
	@PatchMapping("/my-herbs")
	public ResponseEntity<BaseResponse<?>> deleteHerb(@PathParam("myHerbId") Integer myHerbId) {
		// (@AuthenticationPrincipal User user, @PathParam("myHerbId") Integer myHerbId){
		myHerbService.deleteMyHerb(myHerbId);
		return ResponseEntity.ok(new BaseResponse<>(HttpStatus.OK.value(), null));
	}
}
