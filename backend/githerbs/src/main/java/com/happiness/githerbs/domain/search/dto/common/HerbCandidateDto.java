package com.happiness.githerbs.domain.search.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "이미지 검색 결과 허브 후보 DTO")
public record HerbCandidateDto(
	@Schema(description = "허브 번호")
	Integer herbId,
	@Schema(description = "허브 이미지 URL")
	String herbImgUrl,
	@Schema(description = "허브 이름")
	String herbName,
	@Schema(description = "허브 유사도")
	Double similarity
) {
}
