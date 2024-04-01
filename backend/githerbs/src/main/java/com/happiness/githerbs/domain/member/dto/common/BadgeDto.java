package com.happiness.githerbs.domain.member.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "뱃지 DTO")
public record BadgeDto(

	Integer badgeId,
	String badgeTitle,
	String badgeInfo,
	Boolean check
) {
}
