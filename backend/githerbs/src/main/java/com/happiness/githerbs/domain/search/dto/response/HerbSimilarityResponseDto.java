package com.happiness.githerbs.domain.search.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "fastapi에서 반환되는 약초 유사도 DTO")
public record HerbSimilarityResponseDto(
	@Schema(description = "약초 번호 + 약초 이름")
	String herbClass,
	@Schema(description = "약초 유사도")
	Double similarity
) {
}
