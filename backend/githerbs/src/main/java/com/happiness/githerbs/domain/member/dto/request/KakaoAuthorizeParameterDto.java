package com.happiness.githerbs.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "authorize에서 리다이렉트되는 파라메터 DTO")
public record KakaoAuthorizeParameterDto(
	@Schema(description = "카카오 인가 코드")
	String code,

	@Schema(description = "인증 실패 시 반환되는 에러 코드")
	String error,

	@Schema(description = "인증 실패 시 반환되는 에러 메시지")
	String error_description,

	@Schema(description = "요청 시 전달한 state과 동일한 값")
	String state
) {
}
