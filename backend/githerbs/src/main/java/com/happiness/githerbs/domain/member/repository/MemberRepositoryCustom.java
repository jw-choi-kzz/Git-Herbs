package com.happiness.githerbs.domain.member.repository;

import com.happiness.githerbs.domain.member.dto.response.UserInfoResponseDto;
import com.happiness.githerbs.domain.member.entity.Member;

public interface MemberRepositoryCustom {
	void deleteMember(Integer id);
	UserInfoResponseDto updateNickname(Integer id, String nickname);
	UserInfoResponseDto updateProfileImg(Integer id, String imgId);
}
