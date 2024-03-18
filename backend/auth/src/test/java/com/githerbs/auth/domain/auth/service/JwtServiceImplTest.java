package com.githerbs.auth.domain.auth.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.githerbs.auth.domain.auth.dto.common.JwtScopeDto;
import com.githerbs.auth.domain.auth.dto.common.MemberInfoDto;


@SpringBootTest
class JwtServiceImplTest {

    @Autowired
	private JwtService service;

	@Test
	void createToken() {
		var uuid = UUID.randomUUID().toString();
		var memberInfo = MemberInfoDto.builder().memberId(1).memberNickname("test").scope(JwtScopeDto.MEMBER).build();
		var state = "test";


		var result = service.createToken(uuid, memberInfo, state);
		assertNotNull(result);
		assertEquals(result.getDeviceId(), uuid);
	}

	@Test
	void reissueToken() {
	}

	@Test
	void validateToken() {
	}

	@Test
	void revokeToken() {
	}
}