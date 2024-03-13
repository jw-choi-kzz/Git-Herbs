package com.githerbs.auth.domain.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "AuthorizationTokenRequestDto", description = "Authorization token 클래스")
public class AuthorizationTokenRequestDto {
	@Schema(description = "access_token")
	private String accessToken;
	@Schema(description = "refresh_token")
	private String refreshToken;
}
