package com.happiness.githerbs.domain.member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@Schema(description = "사용자 정보 응답")
public record UserInfoResponseDto(
	@Schema(description = "사용자 회원번호")
	Integer id,
	@Schema(description = "사용자 닉네임")
	String nickname,
	@Schema(description = "사용자 프로필 이미지")
	String img
) {
	
}
