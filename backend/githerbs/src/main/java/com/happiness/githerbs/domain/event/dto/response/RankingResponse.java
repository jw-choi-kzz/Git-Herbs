package com.happiness.githerbs.domain.event.dto.response;

public record RankingResponse(
	Integer userId,
	String imgId,
	String nickname
) {
}
