package com.happiness.githerbs.domain.member.repository;

import java.time.LocalDate;
import java.util.List;

import com.happiness.githerbs.domain.event.dto.response.RankingResponse;

public interface MemberDailyRepositoryCustom {

	List<RankingResponse> findRanker(LocalDate start, LocalDate now);

	void updateDailyQuiz(Integer userId, boolean correct);
	void updateDailyBookmark(Integer userId);
}
