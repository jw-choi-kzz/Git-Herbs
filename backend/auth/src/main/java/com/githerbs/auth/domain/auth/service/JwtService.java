package com.githerbs.auth.domain.auth.service;

import com.githerbs.auth.domain.auth.dto.common.MemberInfoDto;
import com.githerbs.auth.domain.auth.dto.common.AuthorizationTokenDto;
import com.githerbs.auth.domain.auth.dto.response.JwtExtrationResponseDto;
import com.githerbs.auth.domain.auth.dto.response.JwtResponseDto;

public interface JwtService {
	JwtResponseDto createToken(String deviceId, MemberInfoDto memberInfo, String state);
	JwtResponseDto reissueToken(String deviceId, AuthorizationTokenDto token, String state);
	JwtExtrationResponseDto validateToken(String deviceId, AuthorizationTokenDto token);
	boolean revokeToken(String deviceId, AuthorizationTokenDto token);

}
