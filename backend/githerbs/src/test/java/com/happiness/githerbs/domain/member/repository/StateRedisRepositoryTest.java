package com.happiness.githerbs.domain.member.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.happiness.githerbs.domain.member.entity.StateRedisEntity;

@SpringBootTest
class StateRedisRepositoryTest {
	@Autowired
	private StateRedisRepository redis;

	@Test
	void save() {
		var entity = StateRedisEntity.builder().state("test").build();
		var result = redis.save(entity);

		assertEquals(entity.getState(), result.getState());
	}

	@Test
	void findById() {
		// var entity = StateRedisEntity.builder().state("test").build();
		// redis.save(entity);
		var state = "test";
		var result = redis.findById(state);
		assertTrue(result.isPresent());
		assertEquals(state, result.get().getState());
	}

	@Test
	void delete() {
		var entity = StateRedisEntity.builder().state("test").build();
		redis.delete(entity);

		var result = redis.findById(entity.getState());
		assertTrue(result.isEmpty());
	}

}