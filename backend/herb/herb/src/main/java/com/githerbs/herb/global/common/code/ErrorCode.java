package com.githerbs.herb.global.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	HERB_NOT_FOUND(404, "HERB-001", "허브를 찾을 수 없는 경우"),

	USER_NOT_FOUND(404, "USER-001", "유저를 찾을 수 없는 경우"),

	BOOKMARK_NOT_FOUND(404, "STAR-001", "즐겨찾기를 찾을 수 없는 경우"),
	BOOKMARK_DUPLICATED(404, "STAR-002", "이미 등록된 즐겨찾기인 경우");

	private final int status;
	private final String code;
	private final String message;
}
