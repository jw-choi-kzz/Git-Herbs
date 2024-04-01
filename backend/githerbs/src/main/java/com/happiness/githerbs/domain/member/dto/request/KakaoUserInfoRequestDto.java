package com.happiness.githerbs.domain.member.dto.request;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "KakaoUserInfoRequestDto", description = "카카오 유저 정보 요청 DTO")
public class KakaoUserInfoRequestDto {

	@Schema(description = "이미지 URL HTTPS 허용 여부")
	private boolean secureResource;
	@Schema(description = "유저 정보 요청 목록")
	private List<String> propertyKeys;
}
