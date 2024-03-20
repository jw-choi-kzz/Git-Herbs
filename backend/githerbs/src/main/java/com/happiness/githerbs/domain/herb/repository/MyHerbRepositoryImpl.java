package com.happiness.githerbs.domain.herb.repository;

import static com.happiness.githerbs.domain.herb.entity.QMyHerb.*;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import com.happiness.githerbs.domain.herb.dto.response.MyHerbResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MyHerbRepositoryImpl implements MyHerbRepositoryCustomer {

	private final JPAQueryFactory queryFactory;

	public Slice<MyHerbResponseDto> findMyHerbListByHerbIdAndMemberId(Integer memberId, Integer herId,
		Pageable pageable) {

		List<MyHerbResponseDto> myHerblist = queryFactory
			.select(Projections.constructor(MyHerbResponseDto.class,
				myHerb.id,
				myHerb.imgId,
				myHerb.similarity,
				myHerb.createdAt))
			.from(myHerb)
			.where(myHerb.herb.id.eq(herId).and(myHerb.member.id.eq(memberId)))
			.orderBy(myHerb.createdAt.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		boolean hasNext = false;
		if (myHerblist.size() > pageable.getPageSize()) {
			myHerblist.remove(pageable.getPageSize());
			hasNext = true;
		}
		return new SliceImpl<>(myHerblist, pageable, hasNext);
	}
}
