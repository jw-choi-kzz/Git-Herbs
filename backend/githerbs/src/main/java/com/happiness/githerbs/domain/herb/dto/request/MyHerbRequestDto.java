package com.happiness.githerbs.domain.herb.dto.request;

public record MyHerbRequestDto(
	Integer herbId,
	String imgId,
	Double similarity
) {
}
