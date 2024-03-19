package com.githerbs.auth.domain.auth.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "AuthorizationTokenDto", description = "Authorization token 클래스")
public class AuthorizationTokenDto {
	@Schema(description = "Access Token(JWT)")
	private String accessToken;
	@Schema(description = "Refresh Token(JWT)")
	private String refreshToken;
}
