package com.happiness.githerbs.domain.herb.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.happiness.githerbs.domain.herb.dto.response.HerbDetailResponseDto;
import com.happiness.githerbs.domain.herb.dto.response.HerbResponseDto;
import com.happiness.githerbs.domain.herb.dto.response.HerbSeasonResponseDto;
import com.happiness.githerbs.domain.herb.service.HerbService;
import com.happiness.githerbs.global.common.response.SuccessResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/herbs")
public class HerbController {

	private final HerbService herbService;

	@GetMapping
	public ResponseEntity<SuccessResponse<Slice<HerbResponseDto>>> getHerbList(Pageable pageable) {
		// getHerbList(@AuthenticationPrincipal User user, Pageable pageable)
		// Integer userId = user.getId()

		Integer userId = 1;
		return ResponseEntity.ok(
			new SuccessResponse<>(HttpStatus.OK.value(), herbService.getHerListByUserId(userId, pageable)));
	}

	@GetMapping("/seasons")
	public ResponseEntity<SuccessResponse<List<HerbSeasonResponseDto>>> getHerbSeasonList() {
		return ResponseEntity.ok(new SuccessResponse<>(HttpStatus.OK.value(), herbService.getHerbSeasonByHerdId()));
	}

	@GetMapping("/{herbId}")
	public ResponseEntity<SuccessResponse<HerbDetailResponseDto>> getHerb(@PathVariable Integer herbId) {
		return ResponseEntity.ok(
			new SuccessResponse<>(HttpStatus.OK.value(), herbService.getHerbDetailByHerbId(herbId)));
	}
}
