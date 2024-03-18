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


	private RedisService service;

	@Test
	void setRefreshToken() {

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