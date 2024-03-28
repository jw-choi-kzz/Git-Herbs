package com.happiness.githerbs.domain.member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "카카오 토큰 응답 DTO")
public record KaKaoTokenResponse(
	@Schema(description = "토큰 타입, bearer로 고정")
	String tokenType,
	@Schema(description = "사용자 액세스 토큰 값")
	String accessToken,
	@Schema(description = "OpenID Connect 확장 기능을 통해 발급되는 ID 토큰 값")
	String idToken,
	@Schema(description = "액세스 토큰과 ID 토큰 만료 시간(초)")
	Integer expiresIn,
	@Schema(description = "사용자 리프레시 토큰 값")
	String refreshToken,
	@Schema(description = "리프레시 토큰 만료 시간(초)")
	Integer refreshTokenExpiresIn,
	@Schema(description = "인증된 사용자의 정보 조회 권한 범위")
	String scope
) {
}
