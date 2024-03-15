package com.githerbs.auth.domain.auth.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;

import com.githerbs.auth.domain.auth.entity.JwtRedisEntity;
import com.githerbs.auth.domain.auth.repository.JwtRedisRepository;

@DataRedisTest
class RedisServiceImplTest {

	@Autowired
	private JwtRedisRepository repo;

	@Test
	void setRefreshToken() {
		var entity = JwtRedisEntity.builder().id("deviceId").memberId("0").refreshToken("asdfasdfasdfasdf").build();
		var result = repo.save(entity);
		assertEquals(result.getId(), "deviceId");
		assertEquals(result.getMemberId(), "0");
		assertEquals(result.getRefreshToken(), "asdfasdfasdfasdf");
	}

	@Test
	void getRefreshToken() {
	}

	@Test
	void getRefreshTokenByMemberId() {
	}

	@Test
	void updateRefreshToken() {
	}

	@Test
	void deleteRefreshToken() {
	}
}