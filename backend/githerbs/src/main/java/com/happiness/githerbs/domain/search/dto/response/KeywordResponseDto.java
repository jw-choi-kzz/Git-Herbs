package com.happiness.githerbs.domain.search.dto.response;

import lombok.Builder;

@Builder
public record KeywordResponseDto(
	Integer herbId,
	String herbName
) {
}
