package com.happiness.githerbs.domain.member.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.happiness.githerbs.domain.member.entity.Member;

@SpringBootTest
class MemberRepositoryTest {
	@Autowired
	MemberRepository repo;

	@Test
	void save() {
		var entity = Member.builder().imgId("").deleted(false).kakaoId(123L).nickname("자유로운 약초꾼99").build();
		var result = repo.save(entity);
		assertNotNull(result);
		assertEquals("자유로운 약초꾼99", result.getNickname());
	}

	@Test
	void findByKakaoIdAndDeleted() {
	}
}