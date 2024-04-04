package com.happiness.githerbs.domain.member.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "토큰 재발급 응답 DTO")
public record ReissueTokenResponseDto(
	@Schema(description = "토큰 타입, bearer로 고정")
	@JsonProperty("token_type")
	String tokenType,
	@Schema(description = "액세스 토큰")
	@JsonProperty("access_token")
	String accessToken,
	@Schema(description = "리프레시 토큰")
	@JsonProperty("refresh_token")
	String refreshToken,
	@Schema(description = "디바이스 아이디")
	@JsonProperty("device_id")
	String deviceId
) {
	// tokenType: 토큰 타입, bearer로 고정
	public ReissueTokenResponseDto{
		if(tokenType == null || tokenType.isBlank() ){
			tokenType = "bearer";
		}
	}
}
