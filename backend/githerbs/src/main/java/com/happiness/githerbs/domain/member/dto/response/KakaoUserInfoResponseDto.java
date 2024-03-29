package com.happiness.githerbs.domain.member.dto.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.happiness.githerbs.domain.member.dto.common.KakaoUserProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "카카오 사용자 정보 응답")
public record KakaoUserInfoResponseDto(
	@Schema(description = "카카오 회원번호")
	Long id,
	@Schema(description = "연결 시각")
	@JsonProperty("connected_at")
	LocalDate connectedAt,
	@Schema(description = "카카오 프로필 정보")
	KakaoUserProperties properties
) {
}
