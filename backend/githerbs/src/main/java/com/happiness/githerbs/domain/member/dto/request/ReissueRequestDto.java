package com.happiness.githerbs.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
@Schema(description = "토큰 재발급 요청 DTO")
public record ReissueRequestDto(
	@Schema(description = "액세스 토큰")
	@NotBlank(message = "액세스 토큰은 필수값입니다")
	String accessToken,
	@Schema(description = "리프레시 토큰")
	@NotBlank(message = "리프레시 토큰은 필수값입니다")
	String refreshToken,
	@Schema(description = "디바이스 아이디")
	@NotBlank(message = "디바이스 아이디는 필수값입니다")
	String deviceId

) {
}
