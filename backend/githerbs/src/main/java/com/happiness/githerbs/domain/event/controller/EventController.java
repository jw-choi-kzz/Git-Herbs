package com.happiness.githerbs.domain.event.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.happiness.githerbs.domain.event.dto.request.QuizRequest;
import com.happiness.githerbs.domain.event.dto.response.MonthlyHerbResponse;
import com.happiness.githerbs.domain.event.dto.response.QuizResponse;
import com.happiness.githerbs.domain.event.dto.response.RankingResponse;
import com.happiness.githerbs.domain.event.service.EventService;
import com.happiness.githerbs.global.common.code.ErrorCode;
import com.happiness.githerbs.global.common.exception.BaseException;
import com.happiness.githerbs.global.common.response.SuccessResponse;

import jakarta.validation.Valid;
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

	@GetMapping("/quiz")
	public ResponseEntity<SuccessResponse<QuizResponse>> findQuiz() {
		return ResponseEntity.ok(new SuccessResponse<>(HttpStatus.OK, eventService.findQuiz()));
	}

	@PostMapping("/quiz")
	public ResponseEntity<SuccessResponse<Boolean>> solveQuiz(@Valid @RequestBody QuizRequest quizRequest,
		BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new BaseException(ErrorCode.NOT_VALID_ERROR);
		}
		return ResponseEntity.ok(new SuccessResponse<>(HttpStatus.OK, eventService.solveQuiz(quizRequest)));
	}

}
