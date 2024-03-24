package com.happiness.githerbs.domain.manual.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Document(
	@JsonProperty("road_address")
	Address roadAddress,
	Address address
) {
	public Address getValidAddress() {
		return address != null ? address : roadAddress;
	}
}
