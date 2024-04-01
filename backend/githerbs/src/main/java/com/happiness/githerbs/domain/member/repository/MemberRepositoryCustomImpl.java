package com.happiness.githerbs.domain.member.repository;

import static com.happiness.githerbs.domain.member.entity.QMember.*;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.happiness.githerbs.domain.member.dto.response.UserInfoResponseDto;
import com.happiness.githerbs.domain.member.entity.Member;
import com.querydsl.core.types.Projections;
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

	@Override
	public UserInfoResponseDto updateNickname(Integer id, String nickname) {
		queryFactory.update(member).set(member.nickname, nickname).where(member.id.eq(id)).execute();
		return queryFactory
			.select(Projections.constructor(
				UserInfoResponseDto.class,
				member.id,
				member.nickname,
				member.imgId))
			.from(member)
			.where(member.id.eq(id))
			.fetchOne();
	}

	@Override
	public UserInfoResponseDto updateProfileImg(Integer id, String imgId) {
		queryFactory.update(member).set(member.imgId, imgId).where(member.id.eq(id)).execute();
		return queryFactory
			.select(Projections.constructor(
				UserInfoResponseDto.class,
				member.id,
				member.nickname,
				member.imgId))
			.from(member)
			.where(member.id.eq(id))
			.fetchOne();
	}

}
