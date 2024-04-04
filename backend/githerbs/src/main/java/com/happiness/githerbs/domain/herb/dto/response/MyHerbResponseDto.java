package com.happiness.githerbs.domain.herb.dto.response;

import java.time.LocalDateTime;

public record MyHerbResponseDto(
	Integer myHerbId,
	String imgId,
	Double similarity,
	LocalDateTime createdAt
) {
}
