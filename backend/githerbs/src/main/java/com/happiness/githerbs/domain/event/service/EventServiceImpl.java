package com.happiness.githerbs.domain.event.service;

import java.time.LocalDate;
import java.util.*;

import com.happiness.githerbs.domain.event.dto.response.BadgeDto;
import com.happiness.githerbs.domain.event.entity.Badge;
import com.happiness.githerbs.domain.member.entity.MemberBadge;
import com.happiness.githerbs.domain.member.repository.MemberBadgeRepository;
import org.springframework.stereotype.Service;

import com.happiness.githerbs.domain.event.dto.response.DailyHerbResponse;
import com.happiness.githerbs.domain.event.dto.response.QuizResponse;
import com.happiness.githerbs.domain.event.dto.response.RankingResponse;
import com.happiness.githerbs.domain.event.entity.Quiz;
import com.happiness.githerbs.domain.event.repository.QuizRepository;
import com.happiness.githerbs.domain.herb.entity.Herb;
import com.happiness.githerbs.domain.herb.repository.HerbDailyRepository;
import com.happiness.githerbs.domain.herb.repository.HerbRepository;
import com.happiness.githerbs.domain.member.entity.MemberDaily;
import com.happiness.githerbs.domain.member.repository.MemberDailyRepository;
import com.happiness.githerbs.domain.member.repository.MemberRepository;
import com.happiness.githerbs.global.common.code.ErrorCode;
import com.happiness.githerbs.global.common.exception.BaseException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

	private final MemberDailyRepository memberDailyRepository;
	private final HerbRepository herbRepository;
	private final QuizRepository quizRepository;
	private final MemberRepository memberRepository;
	private final HerbDailyRepository herbDailyRepository;
	private final MemberBadgeRepository memberBadgeRepository;

	@Override
	public List<RankingResponse> findRanker() {
		LocalDate current = LocalDate.now();
		LocalDate start = current.withDayOfMonth(1);
		return memberDailyRepository.findRanker(start, current);
	}

	@Override
	public DailyHerbResponse findDailyHerb() {
		Integer id = herbDailyRepository.findFirstBy()
			.orElseThrow(() -> new BaseException(ErrorCode.HERB_NOT_FOUND))
			.getHerb()
			.getId();
		Herb herb = herbRepository.findById(id).orElseThrow(() -> new BaseException(ErrorCode.HERB_NOT_FOUND));
		return new DailyHerbResponse(herb.getId(), herb.getHerbImg(), herb.getHerbName(), herb.getHerbMedicalPart());
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

	@Override
	@Transactional
	public boolean solveQuiz(Integer memberId, Integer answer) {
		Optional<MemberDaily> byMemberIdAndDate = memberDailyRepository.findByMemberIdAndDate(memberId,
			LocalDate.now());

		MemberDaily memberDaily;

		if (byMemberIdAndDate.isPresent()) {
			memberDaily = byMemberIdAndDate.get();
		} else {
			memberDaily = MemberDaily
				.builder()
				.member(
					memberRepository.findById(memberId).orElseThrow(() -> new BaseException(
						ErrorCode.USER_NOT_FOUND))
				).date(LocalDate.now())
				.build();
			memberDailyRepository.saveAndFlush(memberDaily);
		}

		if (memberDaily.getDate().equals(LocalDate.now()) && memberDaily.isQuiz()) {
			throw new BaseException(ErrorCode.QUIZ_SOLVED);
		}

		Quiz quiz = quizRepository.findFirstBy().orElseThrow(() -> new BaseException(ErrorCode.INTERNAL_SERVER_ERROR));
		boolean correct = Objects.equals(quiz.getAnswer(), answer);

		memberDailyRepository.updateDailyQuiz(memberId, correct);

		return correct;
	}

	@Override
	public List<BadgeDto> getBadge(Integer memberId) {
		Optional<List<MemberBadge>> memberBadges = memberBadgeRepository.findByMemberId(memberId);
		if( memberBadges.isPresent()){
			List<BadgeDto> badgeDtos = new ArrayList<>();
			for( MemberBadge memberBadge : memberBadges.get()){
				Badge  badge = memberBadge.getBadge();
				badgeDtos.add(BadgeDto.builder()
						.name(badge.getName())
						.details(badge.getDetails())
						.badgeId(badge.getId())
						.build());
			}
			return badgeDtos;
		} else  return Collections.emptyList();
	};

}
