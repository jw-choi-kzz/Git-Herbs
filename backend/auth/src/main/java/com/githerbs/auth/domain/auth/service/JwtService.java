package com.githerbs.auth.domain.auth.service;

import com.githerbs.auth.domain.auth.dto.common.MemberInfoDto;
import com.githerbs.auth.domain.auth.dto.request.AuthorizationTokenDto;
import com.githerbs.auth.domain.auth.dto.request.JwtGrantTypeDto;
import com.githerbs.auth.domain.auth.dto.response.JwtExtrationResponseDto;
import com.githerbs.auth.domain.auth.dto.response.JwtResponseDto;

import jakarta.validation.constraints.NotBlank;

public interface JwtService {
	JwtResponseDto createToken(@NotBlank(message = "grant_type은 필수값입니다.") JwtGrantTypeDto grantType, MemberInfoDto memberInfo, AuthorizationTokenDto token, String state);
	JwtExtrationResponseDto validateToken(AuthorizationTokenDto token);
	boolean revokeToken(AuthorizationTokenDto token);

}
