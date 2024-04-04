package com.happiness.githerbs.domain.event.dto.request;

import org.hibernate.validator.constraints.Range;

public record QuizRequest(
	@Range(min = 0, max = 3)
	Integer answer
) {
}
