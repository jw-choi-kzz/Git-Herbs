package com.happiness.githerbs.domain.auth.dto.request;

import lombok.Getter;

@Getter
public enum JwtGrantTypeDto {
	TOKEN("token"),
	REFRESH("refresh_token");

	private final String grantType;

	JwtGrantTypeDto(String grantType) {
		this.grantType = grantType;
	}
}
