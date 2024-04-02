package com.happiness.githerbs.domain.member.repository;

import static com.happiness.githerbs.domain.member.entity.QMemberDaily.*;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.happiness.githerbs.domain.event.dto.response.RankingResponse;
import com.happiness.githerbs.domain.member.dto.common.GrassDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class MemberDailyRepositoryImpl implements MemberDailyRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	public MemberDailyRepositoryImpl(@Qualifier("queryFactory") JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	public List<GrassDto> findGrass(Integer id) {

		LocalDate now = LocalDate.now();

		return queryFactory
			.select(Projections.constructor(GrassDto.class,
				memberDaily.date,
				getTotalTrueCount()))
			.from(memberDaily)
			.where(memberDaily.member.id.eq(id).and(memberDaily.date.between(now.withDayOfMonth(1), now)))
			.groupBy(memberDaily.date)
			.fetch();
	}

	@Override
	public List<RankingResponse> findRanker(LocalDate start, LocalDate now) {
		return queryFactory.select(
				Projections.constructor(RankingResponse.class,
					memberDaily.member.id,
					memberDaily.member.imgId,
					memberDaily.member.nickname
				))
			.from(memberDaily)
			.where(memberDaily.date.between(start, now))
			.groupBy(memberDaily.member)
			.orderBy(getTotalTrueCount().desc())
			.limit(3)
			.fetch();
	}

	private NumberExpression<Integer> getTotalTrueCount() {
		return (memberDaily.analysis.when(true).then(1).otherwise(0)
			.add(memberDaily.board.when(true).then(1).otherwise(0))
			.add(memberDaily.answer.when(true).then(1).otherwise(0))
			.add(memberDaily.bookmark.when(true).then(1).otherwise(0))
			.add(memberDaily.favorite.when(true).then(1).otherwise(0))
			.add(memberDaily.quiz.when(true).then(1).otherwise(0)))
			.sum();
	}

	@Override
	public void updateDailyQuiz(Integer userId, boolean correct) {
		queryFactory
			.update(memberDaily)
			.set(memberDaily.quiz, true)
			.set(memberDaily.answer, correct)
			.where(memberDaily.member.id.eq(userId).and(memberDaily.date.eq(LocalDate.now())))
			.execute();
	}

	@Override
	public void updateDailyBookmark(Integer userId) {
		queryFactory
			.update(memberDaily)
			.set(memberDaily.bookmark, true)
			.where(memberDaily.member.id.eq(userId).and(memberDaily.date.eq(LocalDate.now())))
			.execute();
	}

}
