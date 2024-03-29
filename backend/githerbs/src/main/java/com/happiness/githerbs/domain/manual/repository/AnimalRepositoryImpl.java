package com.happiness.githerbs.domain.manual.repository;

import static com.happiness.githerbs.domain.manual.entity.QAnimalRegion.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AnimalRepositoryImpl implements AnimalRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<String> findAnimal(Integer regionCode) {
		return queryFactory.select(animalRegion.animal.name)
			.from(animalRegion)
			.where(animalRegion.regionTwoDepth.id.eq(regionCode))
			.fetch();
	}

}
