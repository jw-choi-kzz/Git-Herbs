package com.happiness.githerbs.domain.member.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import feign.form.FormProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "authorize에서 리다이렉트되는 파라메터 DTO")
public record KakaoAuthorizeParameterDto(
	@Schema(description = "카카오 인가 코드")
	@JsonProperty("code")
	@FormProperty("code")
	String code,

	@Schema(description = "인증 실패 시 반환되는 에러 코드")
	@JsonProperty("error")
	@FormProperty("error")
	String error,

	@Schema(description = "인증 실패 시 반환되는 에러 메시지")
	@JsonProperty("error_description")
	@FormProperty("error_description")
	String errorDescription,

	@Schema(description = "요청 시 전달한 state과 동일한 값")
	@JsonProperty("state")
	@FormProperty("state")
	String state
) {
}
