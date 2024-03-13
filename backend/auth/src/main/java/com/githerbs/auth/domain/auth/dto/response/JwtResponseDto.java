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
	private String tokenType = "Bearer";
	@Schema(description = "Access Token(JWT)")
	private String accessToken;
	@Schema(description = "Refresh Token(JWT)")
	private String refreshToken;
}
