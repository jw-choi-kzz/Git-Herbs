package com.happiness.githerbs.domain.manual.repository;

import static com.happiness.githerbs.domain.manual.entity.QTip.*;

import java.time.LocalDate;

import org.springframework.stereotype.Repository;

import com.happiness.githerbs.domain.manual.dto.response.TipResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TipRepositoryImpl implements TipRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public TipResponseDto findTip() {
		return queryFactory
			.select(Projections.constructor(TipResponseDto.class, tip.content))
			.from(tip)
			.where(tip.month.contains(String.valueOf(LocalDate.now().getMonthValue())))
			.orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
			.fetchFirst();
	}
}
