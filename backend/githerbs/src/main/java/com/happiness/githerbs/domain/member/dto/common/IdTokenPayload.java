package com.happiness.githerbs.domain.member.dto.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "IdTokenPayload")
public record IdTokenPayload(
	@Schema(description = "앱 키")
	String aud,
	@Schema(description = "사용자 회원번호")
	String sub,
	@Schema(description = "인증 완료 시각")
	@JsonProperty("auth_time")
	long authTime,
	@Schema(description = "인증 기관")
	String iss,
	@Schema(description = "만료 시간")
	long exp,
	@Schema(description = "발급 시각")
	long iat
) {
}

