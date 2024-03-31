package com.happiness.githerbs.domain.member.service;

import com.happiness.githerbs.domain.member.dto.common.IdTokenPayload;
import com.happiness.githerbs.domain.member.dto.request.KakaoAuthorizeParameterDto;
import com.happiness.githerbs.domain.member.dto.response.UserTokenResponseDto;
import com.happiness.githerbs.domain.member.entity.Member;

public interface MemberService {
	public String loginService();
	public UserTokenResponseDto tokenService(KakaoAuthorizeParameterDto dto);
	public Member registService(String accessToken);
	public boolean withdrawService(String accessToken, String deviceId);
	public IdTokenPayload decodeIdToken(String idToken);
	public String uploadProfile(String profileUrl, Long kakaoId);
}
