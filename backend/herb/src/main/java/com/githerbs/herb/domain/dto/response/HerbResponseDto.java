package com.githerbs.herb.domain.dto.response;

public record HerbResponseDto(
	Integer herbId,
	String herbName,
	Long bookmark,
	Long acquireCheck
) {
}
