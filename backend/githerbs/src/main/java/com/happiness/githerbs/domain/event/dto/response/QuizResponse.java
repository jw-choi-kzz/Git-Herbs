package com.happiness.githerbs.domain.event.dto.response;

public record QuizResponse(
	String question,
	String imgOne,
	String imgTwo,
	String imgThree,
	String imgFour
) {
}
