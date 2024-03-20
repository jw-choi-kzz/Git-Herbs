package com.happiness.githerbs.domain.herb.dto.response;

public record HerbResponseDto(
	Integer herbId,
	String herbName,
	Long bookmark,
	Long acquireCheck
) {
}
