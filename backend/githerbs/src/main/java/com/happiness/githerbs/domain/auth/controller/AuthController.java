package com.happiness.githerbs.domain.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/auth")
@Tag(name = "Auth", description = "Auth API")
public class AuthController {

	@PostMapping("/token")
	@RequestBody(description = "Create a new token")
	public ResponseEntity<?> createToken() {
		return null;
	}

	@GetMapping("/validate")
	public ResponseEntity<?> validateToken() {
		return null;
	}

	@GetMapping("/revoke")
	public ResponseEntity<?> revokeToken() {
		return null;
	}


}
