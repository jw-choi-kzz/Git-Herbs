package com.happiness.githerbs.domain.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.happiness.githerbs.domain.auth.service.JwtService;
import com.happiness.githerbs.domain.member.service.MemberService;

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
	private final JwtService jwt;

	@GetMapping("/login")
	@Operation(summary = "로그인", description = "카카오 로그인")
	public ResponseEntity<?> loginController() {
		return null;
	}

	@DeleteMapping("/")
	@Operation(summary = "회원탈퇴", description = "회원탈퇴")
	public ResponseEntity<?> deleteController() {
		return null;
	}

	@GetMapping("/{userId}")
	@Operation(summary = "회원조회", description = "회원조회(게시판 기능 등에 활용)")
	public ResponseEntity<?> getUserController() {
		return null;
	}

	@DeleteMapping("/logout")
	@Operation(summary = "로그아웃", description = "로그아웃")
	public ResponseEntity<?> logoutController() {
		return null;
	}

	@PostMapping("/reissue")
	@Operation(summary = "토큰재발급", description = "토큰 갱신")
	public ResponseEntity<?> reissueController() {
		return null;
	}

	@PostMapping("/token")
	@Operation(summary = "redirect url", description = "카카오 로그인 redirect url, 카카오 요구사항")
	public ResponseEntity<?> tokenController() {
		return null;
	}
	
}
