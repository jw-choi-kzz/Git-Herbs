package com.happiness.githerbs.domain.auth.service;

import com.happiness.githerbs.domain.auth.dto.common.AuthorizationTokenDto;
import com.happiness.githerbs.domain.auth.dto.common.MemberInfoDto;
import com.happiness.githerbs.domain.auth.dto.response.JwtExtrationResponseDto;
import com.happiness.githerbs.domain.auth.dto.response.JwtResponseDto;

import io.jsonwebtoken.Claims;

public interface JwtService {
	JwtResponseDto createToken(String deviceId, MemberInfoDto memberInfo, String state);
	JwtResponseDto reissueToken(String deviceId, AuthorizationTokenDto token, String state);
	JwtExtrationResponseDto validateToken(String deviceId, AuthorizationTokenDto token);
	JwtExtrationResponseDto validateToken(String accessToken);
	boolean revokeToken(String deviceId, AuthorizationTokenDto token);

	Claims getClaims(String accessToken);
	AuthorizationTokenDto create(MemberInfoDto memberInfo);
	MemberInfoDto convertFromClaims(Claims claims);

}
