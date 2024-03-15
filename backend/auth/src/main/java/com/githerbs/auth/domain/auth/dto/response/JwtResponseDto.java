package com.githerbs.auth.domain.auth.dto.response;

import com.google.gson.annotations.SerializedName;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "JwtResponseDto", description = "JWT 응답 클래스")
public class JwtResponseDto {
	@Builder.Default
	@Schema(description = "JWT 발급 타입")
	private String tokenType = "Bearer";
	@Schema(description = "Access Token(JWT)")
	private String accessToken;
	@Schema(description = "Refresh Token(JWT)")
	private String refreshToken;
	@Schema(description = "무작위 문자열")
	private String state;
	@Schema(description = "기기 고유 아이디")
	private String deviceId;
}
