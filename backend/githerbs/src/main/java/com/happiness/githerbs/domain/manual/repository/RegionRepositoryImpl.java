package com.happiness.githerbs.domain.manual.repository;

import static com.happiness.githerbs.domain.manual.entity.QAnimalRegion.*;
import static com.happiness.githerbs.domain.manual.entity.QRegionName.*;
import static com.happiness.githerbs.domain.manual.entity.QRegionTwoDepth.*;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RegionRepositoryImpl implements RegionRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public Integer getCode(String oneDepth, String twoDepth) {
		return queryFactory
			.select(regionTwoDepth.id)
			.from(regionTwoDepth)
			.where(
				regionTwoDepth.regionOneDepth.id.in(
					queryFactory
						.select(regionName.regionOneDepth.id)
						.from(regionName)
						.where(regionName.name.contains(oneDepth)).fetch()).and(regionTwoDepth.name.contains(twoDepth)))
			.limit(1)
			.fetchOne();
	}

	@Override
	public String getRegion(Integer code) {
		return queryFactory
			.select(regionName.name.concat(" " + queryFactory
				.select(regionTwoDepth.name)
				.from(regionTwoDepth)
				.where(regionTwoDepth.id.eq(code))
				.limit(1)
				.fetchOne()))
			.from(regionName)
			.where(regionName.regionOneDepth.id.eq(
				queryFactory
					.select(regionTwoDepth.regionOneDepth.id)
					.from(regionTwoDepth)
					.where(regionTwoDepth.id.eq(code))
					.fetchOne()))
			.limit(1)
			.fetchOne();
	}

	@Override
	public Integer getRandomCode() {
		return queryFactory.select(animalRegion.regionTwoDepth.id)
			.from(animalRegion)
			.groupBy(animalRegion.regionTwoDepth)
			.orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
			.limit(1)
			.fetchOne();
	}

}
