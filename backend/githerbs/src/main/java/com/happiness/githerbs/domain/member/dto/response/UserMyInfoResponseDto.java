package com.happiness.githerbs.domain.member.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.happiness.githerbs.domain.member.dto.common.GrassDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "유저, 잔디 정보 응답 DTO")
public record UserMyInfoResponseDto(
	@Schema(description = "유저 번호")
	@JsonProperty("user_id")
	Integer userId,
	@Schema(description = "닉네임")
	@JsonProperty("nickname")
	String nickname,
	@Schema(description = "프로필 이미지")
	@JsonProperty("img_id")
	String imgId,
	@Schema(description = "전일 순위")
	@JsonProperty("rank")
	Integer rank,
	@Schema(description = "이번 달 잔디 현황")
	@JsonProperty("grass")
	List<GrassDto> grass

) {
}
