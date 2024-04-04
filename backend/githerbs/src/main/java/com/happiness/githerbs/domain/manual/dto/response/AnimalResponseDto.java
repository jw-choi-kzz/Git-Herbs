package com.happiness.githerbs.domain.manual.dto.response;

import java.util.List;

public record AnimalResponseDto(
	String region,
	List<String> animals
) {
}
