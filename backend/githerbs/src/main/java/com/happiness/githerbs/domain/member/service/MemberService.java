package com.happiness.githerbs.domain.member.service;

import com.happiness.githerbs.domain.auth.dto.common.AuthorizationTokenDto;
import com.happiness.githerbs.domain.member.dto.common.IdTokenPayload;
import com.happiness.githerbs.domain.member.dto.request.KakaoAuthorizeParameterDto;
import com.happiness.githerbs.domain.member.dto.response.ReissueTokenResponseDto;
import com.happiness.githerbs.domain.member.dto.response.UserInfoResponseDto;
import com.happiness.githerbs.domain.member.dto.response.UserTokenResponseDto;
import com.happiness.githerbs.domain.member.entity.Member;

public interface MemberService {
	public String loginService(String redirect);
	public UserTokenResponseDto tokenService(KakaoAuthorizeParameterDto dto);
	public Member registService(String accessToken);
	public Integer withdrawService(String accessToken, String deviceId);
	public UserInfoResponseDto getUserService(int userId);
	public Integer logoutService(String accessToken, String deviceId);
	public ReissueTokenResponseDto reissueService(String deviceId, AuthorizationTokenDto dto);
	public IdTokenPayload decodeIdToken(String idToken);
	public String uploadProfile(String profileUrl, Long kakaoId);
}
