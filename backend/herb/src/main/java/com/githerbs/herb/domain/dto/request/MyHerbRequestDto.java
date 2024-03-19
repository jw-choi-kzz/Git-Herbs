package com.githerbs.herb.domain.dto.request;

public record MyHerbRequestDto(
	Integer herbId,
	String imgId,
	Double similarity
) {
}
