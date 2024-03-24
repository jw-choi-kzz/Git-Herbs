package com.happiness.githerbs.domain.manual.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public final class KakaoLocalResponseDto {

	private Meta meta;
	private List<Document> documents;

}