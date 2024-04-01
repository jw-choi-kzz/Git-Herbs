package com.happiness.githerbs.domain.event.service;

import java.util.List;

import com.happiness.githerbs.domain.event.dto.response.BadgeDto;
import com.happiness.githerbs.domain.event.dto.response.DailyHerbResponse;
import com.happiness.githerbs.domain.event.dto.response.QuizResponse;
import com.happiness.githerbs.domain.event.dto.response.RankingResponse;

public interface EventService {

	DailyHerbResponse findDailyHerb();

	List<RankingResponse> findRanker();

	QuizResponse findQuiz();

	boolean solveQuiz(Integer memberId, Integer answer);

	List<BadgeDto> getBadge(Integer memberId);

}
