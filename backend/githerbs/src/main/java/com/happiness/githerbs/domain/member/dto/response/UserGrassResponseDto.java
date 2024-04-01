package com.happiness.githerbs.domain.member.dto.response;

import java.util.List;

import com.happiness.githerbs.domain.member.dto.common.GrassDto;

public record UserGrassResponseDto(
	Integer userId,
	String nickname,
	String imgId,
	Integer rank,
	List<GrassDto> grass

) {
}
