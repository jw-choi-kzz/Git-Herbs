package com.happiness.githerbs.domain.event.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.happiness.githerbs.domain.event.dto.response.MonthlyHerbResponse;
import com.happiness.githerbs.domain.event.dto.response.RankingResponse;
import com.happiness.githerbs.domain.event.service.EventService;
import com.happiness.githerbs.global.common.response.SuccessResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/event")
public class EventController {

	private final EventService eventService;

	@GetMapping("/rank")
	public ResponseEntity<SuccessResponse<List<RankingResponse>>> findRanker() {
		return ResponseEntity.ok(new SuccessResponse<>(HttpStatus.OK, eventService.findRanker()));
	}

	@GetMapping("/monthly")
	public ResponseEntity<SuccessResponse<MonthlyHerbResponse>> findMonthlyHerb() {
		return ResponseEntity.ok(new SuccessResponse<>(HttpStatus.OK, eventService.findMonthlyHerb()));
	}
}
