package com.happiness.githerbs.domain.member.dto.common;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "잔디 DTO")
public record GrassDto(
	@Schema(description = "날짜")
	LocalDate date,
	@Schema(description = "잔디 개수")
	Integer count
) {
}
