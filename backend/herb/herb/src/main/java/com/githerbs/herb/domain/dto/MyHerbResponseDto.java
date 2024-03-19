package com.githerbs.herb.domain.dto;

import java.time.LocalDateTime;

public record MyHerbResponseDto(
	Integer myHerbId,
	String imgId,
	Double similarity,
	LocalDateTime createdAt
) {
}
