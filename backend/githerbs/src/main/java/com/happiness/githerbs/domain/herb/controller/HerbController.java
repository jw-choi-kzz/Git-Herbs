package com.happiness.githerbs.domain.herb.controller;

import java.util.List;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.happiness.githerbs.domain.auth.service.JwtService;
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
	private final JwtService jwtService;

	@GetMapping
	public ResponseEntity<SuccessResponse<Slice<HerbResponseDto>>> getHerbList(
		@RequestHeader(required = false) String authorization,
		@RequestParam(required = false, defaultValue = "0") int page,
		@RequestParam(required = false, defaultValue = "100")  int size, String criteria) {
		int userId = 0;
		if (authorization != null) {
			userId = jwtService.validateToken(authorization).getMemberId();
		}

		Pageable pageable;
		if (criteria != null) {
			pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, criteria));
		} else
			pageable = PageRequest.of(page, size);

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
