package com.happiness.githerbs.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
@Schema(description = "토큰 요청 DTO")
public record TokenRequestDto(
	@Schema(description = "카카오 인가 코드")
	@NotBlank(message = "카카오 인가 코드는 필수입니다")
	String code,

	@Schema(description = "인증 실패 시 반환되는 에러 코드")
	@NotBlank(message = "에러 코드는 필수입니다")
	String error,

	@Schema(description = "인증 실패 시 반환되는 에러 메시지")
	@NotBlank(message = "에러 메시지는 필수입니다")
	String error_description,

	@Schema(description = "요청 시 전달한 state과 동일한 값")
	String state
) {
}
