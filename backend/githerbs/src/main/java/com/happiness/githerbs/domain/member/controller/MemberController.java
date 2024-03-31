package com.happiness.githerbs.domain.member.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.happiness.githerbs.domain.auth.service.JwtService;
import com.happiness.githerbs.domain.member.dto.request.KakaoAuthorizeParameterDto;
import com.happiness.githerbs.domain.member.dto.response.UserTokenResponseDto;
import com.happiness.githerbs.domain.member.service.MemberService;
import com.happiness.githerbs.global.common.code.ErrorCode;
import com.happiness.githerbs.global.common.exception.BaseException;
import com.happiness.githerbs.global.common.response.SuccessResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Validated
@Slf4j
@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
@Tag(name = "회원", description = "회원 관리")
public class MemberController {

	private final MemberService service;

	@GetMapping("/login")
	@Operation(summary = "로그인", description = "카카오 로그인")
	public ResponseEntity<Object> loginController() {
		var url = service.loginService();
		return ResponseEntity.status(HttpStatus.SEE_OTHER).header("Location", url).build();
	}

	@DeleteMapping("/")
	@Operation(summary = "회원탈퇴", description = "회원탈퇴")
	public ResponseEntity<SuccessResponse<Object>> deleteController(@RequestHeader("device-id") String deviceId, @RequestHeader("Authorization") String accessToken) {
		// withdraw member using service
		var result = service.withdrawService(accessToken, deviceId);
		// send status 200
		return ResponseEntity.ok(new SuccessResponse<>(HttpStatus.OK, null));
	}

	@GetMapping("/{userId}")
	@Operation(summary = "회원조회", description = "회원조회(게시판 기능 등에 활용)")
	public ResponseEntity<?> getUserController() {
		// TODO : get member info using service

		// TODO : return member info
		return null;
	}

	@DeleteMapping("/logout")
	@Operation(summary = "로그아웃", description = "로그아웃")
	public ResponseEntity<?> logoutController() {
		// TODO : validate access token

		// TODO : revoke access token and refresh token using device id

		// TODO : logout kakao account using kakao access token

		// TODO : delete kakao tokens in redis

		// TODO : return status 200
		return null;
	}

	@PostMapping("/reissue")
	@Operation(summary = "토큰재발급", description = "토큰 갱신")
	public ResponseEntity<?> reissueController() {
		// TODO : validate access token and refresh token

		// TODO : reissue access token and refresh token with device id

		// TODO : return new access token and refresh token

		return null;
	}

	@GetMapping("/token")
	@Operation(summary = "redirect url", description = "카카오 로그인 redirect url, 카카오 요구사항")
	public ResponseEntity<SuccessResponse<UserTokenResponseDto>> tokenController(KakaoAuthorizeParameterDto dto) {
		if(dto.error_description() != null) throw new BaseException(dto.error_description(), ErrorCode.USER_KAKAO_FAIL);
		// jwt 발급
		var result = service.tokenService(dto);
		// response에 access jwt, refresh jwt, member 정보 담고, device-id set-cookie 설정하기
		ResponseCookie cookie = ResponseCookie.from("device-id", result.deviceId()).httpOnly(true).path("/").maxAge(60 * 60 * 24 * 30).build();

		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(new SuccessResponse<>(HttpStatus.OK, result));
	}
	
}
