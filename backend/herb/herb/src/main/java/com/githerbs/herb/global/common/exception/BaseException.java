package com.githerbs.herb.global.common.exception;

import com.githerbs.herb.global.common.code.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseException extends RuntimeException {
	private final ErrorCode errorCode;

	public int getStatus() {
		return errorCode.getStatus();
	}

	public String getCode() {
		return errorCode.getCode();
	}
}
