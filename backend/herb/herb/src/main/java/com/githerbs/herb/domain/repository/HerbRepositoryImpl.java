package com.githerbs.herb.domain.repository;

import static com.githerbs.herb.domain.entity.QBookmark.*;
import static com.githerbs.herb.domain.entity.QHerb.*;
import static com.githerbs.herb.domain.entity.QHerbImage.*;
import static com.githerbs.herb.domain.entity.QHerbMedicinalEffect.*;
import static com.githerbs.herb.domain.entity.QMyHerb.*;
import static com.querydsl.core.group.GroupBy.*;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import com.githerbs.herb.domain.dto.HerbDetailResponseDto;
import com.githerbs.herb.domain.dto.HerbImageResponseDto;
import com.githerbs.herb.domain.dto.HerbMedicinalEffectResponseDto;
import com.githerbs.herb.domain.dto.HerbResponseDto;
import com.querydsl.core.types.Projections;
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
					.where(myHerb.herbId.id.eq(herb.id),
						myHerb.memberId.id.eq(userId))
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
			.leftJoin(herbImage).fetchJoin().on(herb.id.eq(herbImage.herb.id))
			.where(herb.id.eq(herbId))
			.transform(
				groupBy(herb.id).list(Projections.constructor(HerbDetailResponseDto.class,
					herb.id, herb.herbName, herb.herbScientificName, herb.herbNickname,
					herb.herbHarvestingTime, herb.herbEnvironment, herb.herbQuality,
					set(Projections.constructor(HerbImageResponseDto.class, herbImage.imgId)),
					set(Projections.constructor(HerbMedicinalEffectResponseDto.class,
						herbMedicinalEffect.medicinalEffect))))
			).get(0);
	}
}


