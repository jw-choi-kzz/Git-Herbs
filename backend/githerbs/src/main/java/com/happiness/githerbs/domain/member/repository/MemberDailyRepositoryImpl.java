package com.happiness.githerbs.domain.member.repository;

import static com.happiness.githerbs.domain.member.entity.QMemberDaily.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.hibernate.metamodel.mapping.SqlExpressible;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.happiness.githerbs.domain.event.dto.response.RankingResponse;
import com.happiness.githerbs.domain.member.dto.common.GrassDto;
import com.happiness.githerbs.domain.member.dto.response.UserRankResponseDto;
import com.happiness.githerbs.domain.member.entity.MemberDaily;
import com.happiness.githerbs.domain.member.entity.QMemberDaily;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLOps;
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

	public Integer findRank(Integer id){
		List<UserRankResponseDto> list = queryFactory
			.select(Projections.constructor(UserRankResponseDto.class,
				memberDaily.member.id,
				getTotalTrueCount()))
			.from(memberDaily)
			.where(memberDaily.date.between(LocalDate.now().withDayOfMonth(1), LocalDate.now()))
			.groupBy(memberDaily.member)
			.fetch();

		int rank = 0;
		for(int i = 0; i < list.size(); i++){
			UserRankResponseDto user = list.get(i);
			if(Objects.equals(user.id(), id)){
				rank = i+1;
				System.out.println(rank);
				break;
			}
		}
		return rank;
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
