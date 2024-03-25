package com.happiness.githerbs.domain.event.dto.response;

public record DailyHerbResponse(
	Integer herbId,
	String herbImg,
	String herbName,
	String herbMedicalPart
) {
}
