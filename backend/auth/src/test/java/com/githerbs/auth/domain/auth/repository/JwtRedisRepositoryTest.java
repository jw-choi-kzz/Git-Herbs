package com.githerbs.auth.domain.auth.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;


import com.githerbs.auth.domain.auth.entity.JwtRedisEntity;

@DataRedisTest
class JwtRedisRepositoryTest {

	@Autowired
	private JwtRedisRepository repo;


	@Test
	void findByMemberId() {


		JwtRedisEntity entity = JwtRedisEntity.builder().id("deviceId").memberId("0").refreshToken("asdfasdfasdfasdf").build();
		var result = repo.save(entity);

		assertEquals(result.getId(), "deviceId");
	}
}