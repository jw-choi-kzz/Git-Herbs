package com.happiness.githerbs.domain.event.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.happiness.githerbs.domain.event.dto.response.MonthlyHerbResponse;
import com.happiness.githerbs.domain.event.dto.response.QuizResponse;
import com.happiness.githerbs.domain.event.dto.response.RankingResponse;
import com.happiness.githerbs.domain.event.entity.Quiz;
import com.happiness.githerbs.domain.event.repository.QuizRepository;
import com.happiness.githerbs.domain.herb.repository.HerbRepository;
import com.happiness.githerbs.domain.member.repository.MemberDailyRepository;
import com.happiness.githerbs.global.common.code.ErrorCode;
import com.happiness.githerbs.global.common.exception.BaseException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

	private final MemberDailyRepository memberDailyRepository;
	private final HerbRepository herbRepository;
	private final QuizRepository quizRepository;

	@Override
	public List<RankingResponse> findRanker() {
		LocalDate current = LocalDate.now();
		LocalDate start = current.withDayOfMonth(1);
		return memberDailyRepository.findRanker(start, current);
	}

	@Override
	public MonthlyHerbResponse findMonthlyHerb() {
		return herbRepository.findMonthlyHerb(LocalDate.now().getMonthValue());
	}

	@Override
	public QuizResponse findQuiz() {
		Quiz quiz = quizRepository.findFirstBy().orElseThrow(() -> new BaseException(ErrorCode.INTERNAL_SERVER_ERROR));
		return new QuizResponse(
			quiz.getQuestion(),
			quiz.getImgOne(),
			quiz.getImgTwo(),
			quiz.getImgThree(),
			quiz.getImgFour()
		);
	}

}
