package com.happiness.githerbs.domain.search.dto.response;

import lombok.Builder;

@Builder
public record keywordResponseDto(
	Integer herbId,
	String herbName
) {
}
