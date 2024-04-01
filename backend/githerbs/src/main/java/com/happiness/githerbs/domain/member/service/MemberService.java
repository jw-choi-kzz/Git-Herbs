package com.happiness.githerbs.domain.member.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.happiness.githerbs.domain.auth.dto.common.AuthorizationTokenDto;
import com.happiness.githerbs.domain.member.dto.common.BadgeDto;
import com.happiness.githerbs.domain.member.dto.common.IdTokenPayload;
import com.happiness.githerbs.domain.member.dto.request.KakaoAuthorizeParameterDto;
import com.happiness.githerbs.domain.member.dto.response.ReissueTokenResponseDto;
import com.happiness.githerbs.domain.member.dto.response.UserGrassResponseDto;
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
	public UserInfoResponseDto nicknameService(String accessToken, String nickname);
	public UserInfoResponseDto profileImgService(String accessToken, MultipartFile img) throws IOException;
	public UserGrassResponseDto userGrassService(String accessToken);
	public List<BadgeDto> badgeService(String accessToken);
	public IdTokenPayload decodeIdToken(String idToken);
	public String uploadProfile(String profileUrl, Long kakaoId);
}
