package com.happiness.githerbs.domain.herb.repository;

import static com.happiness.githerbs.domain.herb.entity.QBookmark.*;
import static com.happiness.githerbs.domain.herb.entity.QHerb.*;
import static com.happiness.githerbs.domain.herb.entity.QHerbMedicinalEffect.*;
import static com.happiness.githerbs.domain.herb.entity.QMyHerb.*;
import static com.querydsl.core.group.GroupBy.*;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import com.happiness.githerbs.domain.herb.dto.response.HerbDetailResponseDto;
import com.happiness.githerbs.domain.herb.dto.response.HerbMedicinalEffectResponseDto;
import com.happiness.githerbs.domain.herb.dto.response.HerbResponseDto;
import com.happiness.githerbs.domain.herb.dto.response.HerbSeasonResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HerbRepositoryImpl implements HerbRepositoryCustomer {

	private final JPAQueryFactory queryFactory;

	@Override
	public Slice<HerbResponseDto> findHerbListByUserId(Integer userId, Pageable pageable) {
		List<HerbResponseDto> herblist = queryFactory
			.select(Projections.constructor(HerbResponseDto.class,
				herb.id,
				herb.herbName,
				JPAExpressions
					.select(bookmark.count())
					.from(bookmark)
					.where(bookmark.herb.id.eq(herb.id),
						bookmark.member.id.eq(userId)),
				JPAExpressions
					.select(myHerb.count())
					.from(myHerb)
					.where(myHerb.herb.id.eq(herb.id),
						myHerb.member.id.eq(userId))
			))
			.from(herb)
			.orderBy(herb.herbName.asc()) // pageable.getSort()
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();
		boolean hasNext = false;
		if (herblist.size() > pageable.getPageSize()) {
			herblist.remove(pageable.getPageSize());
			hasNext = true;
		}
		return new SliceImpl<>(herblist, pageable, hasNext);
	}

	@Override
	public HerbDetailResponseDto findHerbDetailByHerbId(Integer herbId) {
		return queryFactory
			.selectFrom(herb)
			.leftJoin(herbMedicinalEffect).fetchJoin().on(herb.id.eq(herbMedicinalEffect.herb.id))
			.where(herb.id.eq(herbId))
			.transform(
				groupBy(herb.id).list(Projections.constructor(HerbDetailResponseDto.class,
					herb.id, herb.herbName, herb.herbImg, herb.herbScientificName, herb.herbNickname,
					herb.herbMedicalPart, herb.herbHarvestingTime, herb.herbEnvironment, herb.herbQuality,
					set(Projections.constructor(HerbMedicinalEffectResponseDto.class,
						herbMedicinalEffect.medicinalEffect))))
			).get(0);
	}

	@Override
	public List<HerbSeasonResponseDto> findHerbSeasonListByDate() {
		return queryFactory
			.select(Projections.constructor(HerbSeasonResponseDto.class,
				herb.id,
				herb.herbImg,
				herb.herbName))
			.from(herb)
			.where(herb.herbHarvestingTime.like(LocalDateTime.now().getMonthValue() + "월"))
			.orderBy(Expressions.numberTemplate(Double.class, "function('rand')").asc())
			.limit(3)
			.fetch();
	}
}


