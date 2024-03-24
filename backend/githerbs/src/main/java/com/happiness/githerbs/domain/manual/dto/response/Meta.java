package com.happiness.githerbs.domain.manual.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Meta(
	@JsonProperty("total_count")
	int totalCount
) {
}
