package com.happiness.githerbs.domain.member.service;

import com.happiness.githerbs.domain.member.dto.request.KakaoAuthorizeParameterDto;
import com.happiness.githerbs.domain.member.dto.response.UserTokenResponseDto;

public interface MemberService {
	public String loginService();
	public UserTokenResponseDto tokenService(KakaoAuthorizeParameterDto dto);

}
