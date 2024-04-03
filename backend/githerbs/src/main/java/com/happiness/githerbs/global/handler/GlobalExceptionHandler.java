package com.happiness.githerbs.global.handler;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonParseException;
import com.happiness.githerbs.global.common.code.ErrorCode;
import com.happiness.githerbs.global.common.exception.BaseException;
import com.happiness.githerbs.global.common.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

	/** Valid 어노테이션 검증 실패시 발생하는 Exception */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.error("MethodArgumentNotValidException", e);
		BindingResult bindingResult = e.getBindingResult();
		StringBuilder msg = new StringBuilder();
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			msg.append(fieldError.getField()).append(":");
			msg.append(fieldError.getDefaultMessage());
			msg.append(", ");
		}
		final ErrorResponse res = ErrorResponse.of(ErrorCode.NOT_VALID_ERROR, msg.toString());
		return ResponseEntity.status(ErrorCode.NOT_VALID_ERROR.getStatus()).body(res);
	}

	/** Header가 없을 때 발생하는 Exception  */
	@ExceptionHandler(MissingRequestHeaderException.class)
	protected ResponseEntity<ErrorResponse> handleMissingRequestHeaderException(MissingRequestHeaderException e) {
		log.error("MissingRequestHeaderException", e);
		final ErrorResponse res = ErrorResponse.of(ErrorCode.NOT_VALID_HEADER_ERROR, e.getMessage());
		return ResponseEntity.status(ErrorCode.NOT_VALID_HEADER_ERROR.getStatus()).body(res);
	}

	/** Body로 정상적인 데이터가 넘어오지 않을 때 발생하는 Exception  */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	protected ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		log.error("HttpMessageNotReadableException", e);
		final ErrorResponse res = ErrorResponse.of(ErrorCode.REQUEST_BODY_MISSING_ERROR, e.getMessage());
		return ResponseEntity.status(ErrorCode.REQUEST_BODY_MISSING_ERROR.getStatus()).body(res);
	}

	/** Request Parameter로 데이터가 전달되지 않을 때 발생하는 Exception  */
	@ExceptionHandler(MissingServletRequestParameterException.class)
	protected ResponseEntity<ErrorResponse> handleMissingServletRequestParameterException(
		MissingServletRequestParameterException e) {
		log.error("MissingServletRequestParameterException", e);
		final ErrorResponse res = ErrorResponse.of(ErrorCode.MISSING_REQUEST_PARAMETER_ERROR, e.getMessage());
		return ResponseEntity.status(ErrorCode.MISSING_REQUEST_PARAMETER_ERROR.getStatus()).body(res);
	}
	
	/** HTTP Method가 잘못되었을 때 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	protected  ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		log.error("HttpRequestMethodNotSupportedException", e);
		final ErrorResponse res = ErrorResponse.of(ErrorCode.METHOD_NOT_ALLOWED_ERROR, e.getMessage());
		return ResponseEntity.status(ErrorCode.METHOD_NOT_ALLOWED_ERROR.getStatus()).body(res);
	}
	
	/** cookie 값이 없을 때 */
	@ExceptionHandler(MissingRequestCookieException.class)
	protected ResponseEntity<ErrorResponse> handleMissingRequestCookieException(MissingRequestCookieException e) {
		log.error("MissingRequestCookieException", e);
		final ErrorResponse res = ErrorResponse.of(ErrorCode.NOT_VALID_HEADER_ERROR, e.getMessage());
		return ResponseEntity.status(ErrorCode.NOT_VALID_HEADER_ERROR.getStatus()).body(res);
	}

	/** 응답이 잘못된 경우 (FeignClient에서 404가 오는 등)에 발생하는 Exception */
	@ExceptionHandler(HttpClientErrorException.class)
	protected ResponseEntity<ErrorResponse> handleHttpClientErrorException(HttpClientErrorException e) {
		log.error("HttpClientErrorException", e);
		final ErrorResponse res = ErrorResponse.of(ErrorCode.BAD_REQUEST_ERROR, e.getMessage());
		return ResponseEntity.status(ErrorCode.BAD_REQUEST_ERROR.getStatus()).body(res);
	}

	/** 잘못된 주소로 요청이 온 경우에 발생하는 Exception */
	@ExceptionHandler(NoHandlerFoundException.class)
	protected ResponseEntity<ErrorResponse> handleNoHandlerFoundException(NoHandlerFoundException e) {
		log.error("NoHandlerFoundException", e);
		final ErrorResponse res = ErrorResponse.of(ErrorCode.NOT_FOUND_ERROR, e.getMessage());
		return ResponseEntity.status(ErrorCode.NOT_FOUND_ERROR.getStatus()).body(res);
	}

	/** NULL Pointer Exception  */
	@ExceptionHandler(NullPointerException.class)
	protected ResponseEntity<ErrorResponse> handleNullPointerException(NullPointerException e) {
		log.error("NullPointerException", e);
		final ErrorResponse res = ErrorResponse.of(ErrorCode.NULL_POINT_ERROR, e.getMessage());
		return ResponseEntity.status(ErrorCode.NULL_POINT_ERROR.getStatus()).body(res);
	}

	/** IOException */
	@ExceptionHandler(IOException.class)
	protected ResponseEntity<ErrorResponse> handleIOException(IOException e) {
		log.error("IOException", e);
		final ErrorResponse res = ErrorResponse.of(ErrorCode.IO_ERROR, e.getMessage());
		return ResponseEntity.status(ErrorCode.IO_ERROR.getStatus()).body(res);
	}

	/** gson exception */
	@ExceptionHandler(JsonParseException.class)
	protected ResponseEntity<ErrorResponse> handleJsonParseException(JsonParseException e) {
		log.error("JsonParseException", e);
		final ErrorResponse res = ErrorResponse.of(ErrorCode.JSON_PARSE_ERROR, e.getMessage());
		return ResponseEntity.status(ErrorCode.JSON_PARSE_ERROR.getStatus()).body(res);
	}

	/** jackson exception */
	@ExceptionHandler(JsonProcessingException.class)
	protected ResponseEntity<ErrorResponse> handleJsonProcessingException(JsonProcessingException e) {
		log.error("JsonProcessingException", e);
		final ErrorResponse res = ErrorResponse.of(ErrorCode.JSON_PARSE_ERROR, e.getMessage());
		return ResponseEntity.status(ErrorCode.JSON_PARSE_ERROR.getStatus()).body(res);
	}

	/** feign exception */
	@ExceptionHandler(FeignException.class)
	protected ResponseEntity<ErrorResponse> handleFeignException(FeignException e) {
		log.error("FeignException", e);
		final ErrorResponse res = ErrorResponse.of(ErrorCode.FEIGN_ERROR, e.getMessage());
		return ResponseEntity.status(ErrorCode.FEIGN_ERROR.getStatus()).body(res);
	}

	/** 기타 모든 Excpetion */
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ErrorResponse> handleException(Exception e) {
		log.error("Exception", e);
		final ErrorResponse res = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR, e.getMessage());
		return ResponseEntity.status(ErrorCode.INTERNAL_SERVER_ERROR.getStatus()).body(res);
	}

	/** Business Exception */
	@ExceptionHandler(BaseException.class)
	protected ResponseEntity<ErrorResponse> handleBusinessException(BaseException e) {
		final ErrorResponse res = ErrorResponse.of(e.getErrorCode(), e.getMessage());
		return ResponseEntity.status(e.getErrorCode().getStatus()).body(res);
	}

	// @MessageExceptionHandler(BaseException.class)
	// @SendToUser("/sub/errors")
	// public ErrorResponse handleMessageBaseException(BaseException e) {
	// 	return new ErrorResponse(e.getStatus(), e.getCode());
	// }
}