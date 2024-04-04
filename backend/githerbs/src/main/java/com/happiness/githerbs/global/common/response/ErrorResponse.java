package com.happiness.githerbs.global.common.response;

import com.happiness.githerbs.global.common.code.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
	// http status code
	private int status;
	// 에러 구분 코드
	private String code;
	// 에러 메시지
	private String message;

	public ErrorResponse(ErrorCode errorCode) {
		this.status = errorCode.getStatus();
		this.code = errorCode.getCode();
		this.message = errorCode.getMessage();
	}

	public ErrorResponse(ErrorCode errorCode, String reason) {
		this.status = errorCode.getStatus();
		this.code = errorCode.getCode();
		this.message = reason;
	}

	public static ErrorResponse of(ErrorCode errorCode) {
		return new ErrorResponse(errorCode);
	}

	public static ErrorResponse of(ErrorCode errorCode, String reason) {
		return new ErrorResponse(errorCode, reason);
	}


}