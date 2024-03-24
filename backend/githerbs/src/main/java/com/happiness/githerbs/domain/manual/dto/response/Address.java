package com.happiness.githerbs.domain.manual.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Address(
	@JsonProperty("region_1depth_name")
	String region1depthName,
	@JsonProperty("region_2depth_name")
	String region2depthName
) {
}
