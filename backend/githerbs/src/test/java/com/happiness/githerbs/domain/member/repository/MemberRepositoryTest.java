package com.happiness.githerbs.domain.member.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
	void find() {
		var result  = repo.findById(1);
		assertTrue(result.isPresent());
		assertEquals("1", result.get().getNickname());
	}

	@Test
	@Transactional
	void deleteMember() {
		repo.deleteMember(3);
		var result = repo.findById(3);
		assertEquals(true, result.get().getDeleted());
	}
}