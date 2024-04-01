package com.happiness.githerbs.domain.member.dto.response;

import java.util.Objects;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
@Schema(description = "토큰 응답 DTO")
public record UserTokenResponseDto(
	@Schema(description = "토큰 타입")
	String tokenType,
	@Schema(description = "액세스 토큰")
	@NotBlank(message = "액세스 토큰은 필수값입니다")
	String accessToken,
	@Schema(description = "리프레시 토큰")
	@NotBlank(message = "리프레시 토큰은 필수값입니다")
	String refreshToken,
	@Schema(description = "디바이스 아이디")
	@NotBlank(message = "디바이스 아이디는 필수값입니다")
	String deviceId,
	@Schema(description = "유저 아이디")
	@NotBlank(message = "유저 아이디는 필수값입니다")
	Integer userId,
	@Schema(description = "닉네임")
	@NotBlank(message = "닉네임은 필수값입니다")
	String nickname,
	@Schema(description = "프로필 이미지")
	@NotBlank(message = "프로필 이미지는 필수값입니다")
	String imgId,
	@Schema(description = "리다이렉트 URI")
	String redirectUri
) {
	public UserTokenResponseDto {
		if (Objects.isNull(tokenType) || tokenType.isBlank())
			tokenType = "Bearer";
	}
}
