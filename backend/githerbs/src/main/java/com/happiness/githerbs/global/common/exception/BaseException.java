package com.happiness.githerbs.global.common.exception;

import com.happiness.githerbs.global.common.code.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class BaseException extends RuntimeException {
	private final ErrorCode errorCode;

	public BaseException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}

	public BaseException(String message, ErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public int getStatus() {
		return errorCode.getStatus();
	}

	public String getCode() {
		return errorCode.getCode();
	}

}
