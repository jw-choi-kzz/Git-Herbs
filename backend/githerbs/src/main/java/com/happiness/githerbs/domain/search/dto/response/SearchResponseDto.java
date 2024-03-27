package com.happiness.githerbs.domain.search.dto.response;

import lombok.Builder;

@Builder
public record SearchResponseDto(
	Integer id,
	String herbImg,
	String herbName,
	String scientificName,
	String medicinalPart,
	String description

) {
}
