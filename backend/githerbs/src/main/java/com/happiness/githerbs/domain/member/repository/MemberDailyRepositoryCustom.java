package com.happiness.githerbs.domain.member.repository;

import java.time.LocalDate;
import java.util.List;

import com.happiness.githerbs.domain.event.dto.response.RankingResponse;
import com.happiness.githerbs.domain.member.dto.common.GrassDto;

public interface MemberDailyRepositoryCustom {

	Integer findRank(Integer id);
	List<GrassDto> findGrass(Integer id);
	List<RankingResponse> findRanker(LocalDate start, LocalDate now);
	void updateDailyQuiz(Integer userId, boolean correct);
	void updateDailyBookmark(Integer userId);
	void updateDailyAnalysis(Integer userId);
	void updateDailyBoard(Integer userId);
	void updateDailyFavorite(Integer userId);
}
