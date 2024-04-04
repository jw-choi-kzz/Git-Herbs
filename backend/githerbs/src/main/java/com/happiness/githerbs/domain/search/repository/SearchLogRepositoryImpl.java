package com.happiness.githerbs.domain.search.repository;

import static com.happiness.githerbs.domain.search.entity.QSearchLog.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.happiness.githerbs.domain.search.dto.response.RecentSearchResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SearchLogRepositoryImpl implements SearchLogRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<RecentSearchResponseDto> findRecent(Integer memberId) {
		return queryFactory.select(Projections.constructor(RecentSearchResponseDto.class, searchLog.keyword))
			.from(searchLog)
			.where(searchLog.member.id.eq(memberId))
			.orderBy(searchLog.id.desc())
			.limit(3)
			.fetch();
	}

}
