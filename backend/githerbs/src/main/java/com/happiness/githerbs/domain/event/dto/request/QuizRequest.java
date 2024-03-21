package com.happiness.githerbs.domain.event.dto.request;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.Positive;

public record QuizRequest(
	@Positive
	Integer userId,
	@Range(min = 0, max = 3)
	Integer answer
) {
}
