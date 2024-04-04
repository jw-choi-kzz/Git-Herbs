package com.happiness.githerbs.domain.member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "카카오 로그아웃 응답")
public record KakaoLogoutResponseDto(
	Long id
) {
}
