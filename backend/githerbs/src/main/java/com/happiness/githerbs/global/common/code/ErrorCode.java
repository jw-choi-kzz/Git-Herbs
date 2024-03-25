package com.happiness.githerbs.global.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	/**
	 * HTTP Status Code
	 * 400 : Bad Request
	 * 401 : Unauthorized
	 * 403 : Forbidden
	 * 404 : Not Found
	 * 500 : Internal Server Error
	 *
	 */
	// 잘못된 서버 요청
	BAD_REQUEST_ERROR(400, "G-001", "Bad Request Exception"),
	// @RequestBody 데이터 미 존재
	REQUEST_BODY_MISSING_ERROR(400, "G-002", "Required request body is missing"),
	// 유효하지 않은 타입
	INVALID_TYPE_VALUE(400, "G-003", " Invalid Type Value"),
	// Request Parameter 로 데이터가 전달되지 않을 경우
	MISSING_REQUEST_PARAMETER_ERROR(400, "G-004", "Missing RequestParameter Exception"),
	// 입력/출력 값이 유효하지 않음
	IO_ERROR(400, "G-005", "I/O Exception"),
	// com.google.gson JSON 파싱 실패
	JSON_PARSE_ERROR(400, "G-006", "JsonParseException"),
	// com.fasterxml.jackson.core Processing Error
	JACKSON_PROCESS_ERROR(400, "G-007", "com.fasterxml.jackson.core Exception"),
	// 권한이 없음
	FORBIDDEN_ERROR(403, "G-008", "Forbidden Exception"),
	// 서버로 요청한 리소스가 존재하지 않음
	NOT_FOUND_ERROR(404, "G-009", "Not Found Exception"),
	// NULL Point Exception 발생
	NULL_POINT_ERROR(404, "G-010", "Null Point Exception"),
	// @RequestBody 및 @RequestParam, @PathVariable 값이 유효하지 않음
	NOT_VALID_ERROR(400, "G-011", "handle Validation Exception"),
	// Header 값이 없음
	NOT_VALID_HEADER_ERROR(400, "G-012", "Missing Header Exception"),
	// Token 기간 만료
	EXPIRED_TOKEN_ERROR(401, "G-013", "Expired Token Exception"),
	// 잘못된 Token
	SECURITY_TOKEN_ERROR(401, "G-014", "Security Token Exception"),
	// 지원하지 않은 Token
	UNSUPPORTED_TOKEN_ERROR(401, "G-015", "Unsupported Token Exception"),
	// 잘못된 Token
	WRONG_TOKEN_ERROR(401, "G-016", "Wrong Token Exception"),
	// 사용자의 Token이 아님
	NOT_MATCH_TOKEN_ERROR(401, "G-017", "Not Match Token Exception"),
	// 이미 토큰을 발행함
	EXIST_TOKEN_ERROR(401, "G-018", "Exist Token Exception"),
	// 서버가 처리 할 방법을 모르는 경우 발생
	INTERNAL_SERVER_ERROR(500, "G-999", "Internal Server Error Exception"),


	/**  Business Exception */

	BOARD_NOT_FOUND(404,"BOARD-001","게시글을 찾을 수 없습니다."),
	NOT_MATCH_MEMBER(401,"BOARD-002","게시글을 삭제할 권한이 없습니다."),
	NOT_VALID_FAVORITE(400 , "FAVORITE-001","좋아요를 누를 수 없는 게시글입니다."),

	HERB_NOT_FOUND(404, "HERB-001", "허브를 찾을 수 없는 경우"),

	USER_NOT_FOUND(404, "USER-001", "유저를 찾을 수 없는 경우"),

	MY_HERB_NOT_FOUND(404, "MYHERB-001", "내 도감 사진을 찾을 수 없는 경우"),

	BOOKMARK_NOT_FOUND(404, "STAR-001", "즐겨찾기를 찾을 수 없는 경우"),
	BOOKMARK_DUPLICATED(404, "STAR-002", "이미 등록된 즐겨찾기인 경우"),

	LOCATION_ERROR(404,"LOCATION-001","위치를 찾을 수 없는 경우"),
	REGION_ERROR(404,"REGION-001","주소가 없는 경우"),

	QUIZ_SOLVED(404, "QUIZ-001", "이미 퀴즈에 참여한 경우");

	private final int status;
	private final String code;
	private final String message;
}
