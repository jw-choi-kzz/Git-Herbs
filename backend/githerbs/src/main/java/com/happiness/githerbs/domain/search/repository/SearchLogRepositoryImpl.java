package com.happiness.githerbs.domain.search.repository;

import static com.happiness.githerbs.domain.search.entity.QSearchLog.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SearchLogRepositoryImpl implements SearchLogRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<String> findRecent(Integer memberId) {
		return queryFactory.select(searchLog.keyword)
			.from(searchLog)
			.where(searchLog.member.id.eq(memberId))
			.orderBy(searchLog.id.desc())
			.limit(3)
			.fetch();
	}

}
