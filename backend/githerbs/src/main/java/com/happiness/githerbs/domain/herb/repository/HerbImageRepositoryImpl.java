package com.happiness.githerbs.domain.herb.repository;

import static com.happiness.githerbs.domain.herb.entity.QHerbImage.*;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HerbImageRepositoryImpl implements HerbImageRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public String findByHerb(Integer herbId) {
		return queryFactory
			.select(herbImage.imgId)
			.from(herbImage)
			.where(herbImage.herb.id.eq(herbId))
			.orderBy(Expressions.numberTemplate(Double.class, "RAND()").asc())
			.limit(1)
			.fetchOne();
	}

}
