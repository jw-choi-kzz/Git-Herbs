package com.githerbs.herb.domain.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.githerbs.herb.domain.dto.HerbDetailResponseDto;
import com.githerbs.herb.domain.dto.HerbResponseDto;
import com.githerbs.herb.domain.service.HerbService;
import com.githerbs.herb.global.common.response.BaseResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/herbs")
public class HerbController {

	private final HerbService herbService;

	@GetMapping
	public ResponseEntity<BaseResponse<Slice<HerbResponseDto>>> getHerbList(Pageable pageable) {
		// getHerbList(@AuthenticationPrincipal User user, Pageable pageable)
		// Integer userId = user.getId()

		Integer userId = 1;
		return ResponseEntity.ok(
			new BaseResponse<>(HttpStatus.OK.value(), herbService.getHerListByUserId(userId, pageable)));
	}

	@GetMapping("/{herbId}")
	public ResponseEntity<BaseResponse<HerbDetailResponseDto>> getHerb(@PathVariable Integer herbId) {
		return ResponseEntity.ok(
			new BaseResponse<>(HttpStatus.OK.value(), herbService.getHerbDetailByHerbId(herbId)));
	}
}
