package com.happiness.githerbs.domain.member.dto.common;

import java.time.LocalDate;

public record GrassDto(
	LocalDate date,
	Integer count
) {
}
