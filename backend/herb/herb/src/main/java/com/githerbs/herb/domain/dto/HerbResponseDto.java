package com.githerbs.herb.domain.dto;

public record HerbResponseDto(
	Integer herbId,
	String herbName,
	Long bookmark,
	Long acquireCheck
) {
}
