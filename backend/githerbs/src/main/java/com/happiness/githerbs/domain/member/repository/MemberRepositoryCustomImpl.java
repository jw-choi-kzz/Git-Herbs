package com.happiness.githerbs.domain.member.repository;

import static com.happiness.githerbs.domain.member.entity.QMember.*;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom{
	private final JPAQueryFactory queryFactory;

	public MemberRepositoryCustomImpl(@Qualifier("queryFactory") JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	@Override
	public void deleteMember(Integer id) {
		queryFactory.update(member).set(member.deleted, true).where(member.id.eq(id)).execute();
	}
}
