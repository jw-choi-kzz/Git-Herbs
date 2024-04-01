package com.happiness.githerbs.domain.member.dto.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "뱃지 DTO")
public record BadgeDto(
	@Schema(description = "뱃지 아이디")
	@JsonProperty("badge_id")
	Integer badgeId,
	@Schema(description = "뱃지 이름")
	@JsonProperty("badge_title")
	String badgeTitle,
	@Schema(description = "뱃지 설명")
	@JsonProperty("badge_info")
	String badgeInfo,
	@Schema(description = "획득여부")
	@JsonProperty("check")
	Boolean check
) {
}
