package com.happiness.githerbs.domain.member.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.happiness.githerbs.domain.auth.dto.common.AuthorizationTokenDto;
import com.happiness.githerbs.domain.member.dto.common.BadgeDto;
import com.happiness.githerbs.domain.member.dto.common.IdTokenPayload;
import com.happiness.githerbs.domain.member.dto.request.KakaoAuthorizeParameterDto;
import com.happiness.githerbs.domain.member.dto.response.ReissueTokenResponseDto;
import com.happiness.githerbs.domain.member.dto.response.UserMyInfoResponseDto;
import com.happiness.githerbs.domain.member.dto.response.UserInfoResponseDto;
import com.happiness.githerbs.domain.member.dto.response.UserTokenResponseDto;
import com.happiness.githerbs.domain.member.entity.Member;

public interface MemberService {
	String loginService(String redirect);
	UserTokenResponseDto tokenService(KakaoAuthorizeParameterDto dto);
	Member registService(String accessToken);
	Integer withdrawService(String accessToken, String deviceId);
	UserInfoResponseDto getUserService(int userId);
	Integer logoutService(String accessToken, String deviceId);
	ReissueTokenResponseDto reissueService(String deviceId, AuthorizationTokenDto dto);
	UserInfoResponseDto nicknameService(String accessToken, String nickname);
	UserInfoResponseDto profileImgService(String accessToken, MultipartFile img) throws IOException;
	UserMyInfoResponseDto userMyInfoService(String accessToken);
	List<BadgeDto> badgeService(String accessToken);
	IdTokenPayload decodeIdToken(String idToken);
	String uploadProfile(String profileUrl, Long kakaoId);
}
