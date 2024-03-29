package com.happiness.githerbs.domain.member.dto.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "KakaoUserProperties", description = "카카오 유저 정보")
public record KakaoUserProperties(
	@Schema(description = "카카오 닉네임")
	String nickname,
	@Schema(description = "카카오 프로필 이미지")
	@JsonProperty("profile_image")
	String profileImage,
	@Schema(description = "카카오 썸네일 이미지")
	@JsonProperty("thumbnail_image")
	String thumbnailImage
) {
}
