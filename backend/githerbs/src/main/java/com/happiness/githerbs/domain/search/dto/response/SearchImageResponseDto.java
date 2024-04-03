package com.happiness.githerbs.domain.search.dto.response;

import java.util.List;

import com.happiness.githerbs.domain.search.dto.common.HerbCandidateDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "이미지 검색 응답 DTO")
public record SearchImageResponseDto(
	@Schema(description = "이미지 S3 URI")
	String pictureUrl,
	@Schema(description = "유사 약초 후보 리스트")
	List<HerbCandidateDto> candidates
) {
}
