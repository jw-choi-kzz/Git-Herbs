package com.githerbs.herb.domain.dto;

public record MyHerbRequestDto(
	Integer herbId,
	String imgId,
	Double similarity
) {
}
