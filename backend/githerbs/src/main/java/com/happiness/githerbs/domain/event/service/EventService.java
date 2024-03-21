package com.happiness.githerbs.domain.event.service;

import java.util.List;

import com.happiness.githerbs.domain.event.dto.request.QuizRequest;
import com.happiness.githerbs.domain.event.dto.response.MonthlyHerbResponse;
import com.happiness.githerbs.domain.event.dto.response.QuizResponse;
import com.happiness.githerbs.domain.event.dto.response.RankingResponse;

public interface EventService {

	MonthlyHerbResponse findMonthlyHerb();

	List<RankingResponse> findRanker();

	QuizResponse findQuiz();

	boolean solveQuiz(QuizRequest quizRequest);

}
