package com.githerbs.auth.domain.auth.dto.common;

import lombok.Getter;

@Getter
public enum JwtScopeDto {
	MEMBER("member"),
	ADMIN("admin");

	private final String scope;

	JwtScopeDto(String scope) {
		this.scope = scope;
	}
}
