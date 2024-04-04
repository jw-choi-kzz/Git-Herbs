package com.happiness.githerbs.domain.auth.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.happiness.githerbs.domain.auth.dto.common.AuthorizationTokenDto;
import com.happiness.githerbs.domain.auth.dto.common.JwtScopeDto;
import com.happiness.githerbs.domain.auth.dto.common.MemberInfoDto;

@SpringBootTest
class JwtServiceImplTest {

	@Autowired
	private JwtService service;

	@Test
	void createToken() {
		var uuid = UUID.randomUUID().toString();
		var memberInfo = MemberInfoDto.builder()
			.memberId(1)
			.memberNickname("test")
			.build();
		var state = "test";

		var result = service.createToken(uuid, memberInfo, state);

		assertNotNull(result);
		System.out.println("device-id: " + result.getDeviceId());
		System.out.println("state: " + result.getState());
		System.out.println("access-token: " + result.getAccessToken());
		assertEquals(result.getDeviceId(), uuid);
		assertEquals(result.getState(), state);

	}

	@Test
	void reissueToken() {
		String accessToken = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhY2Nlc3MtdG9rZW4iLCJleHAiOjE3MTE2ODc1NDYsImlkIjoxLCJuaWNrbmFtZSI6InRlc3QiLCJzY29wZSI6Ik1FTUJFUiJ9.E0iAlVVP70Br8EAOVgSi2q63--Da7No0Pdtg5LESqgWEx6rmy58NKj9CSxi4Dq8fjoW-RKdBXj0m71oJJECwnw";
		String refreshToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyZWZyZXNoLXRva2VuIiwiZXhwIjoxNzEyMjA1OTQ2LCJpZCI6MX0.K1b_jGypx-ubLnGSmcJ--p1e7TcZdL9oIeJXdPDFtDcoMEanXA87Fo2RwSLW5o8O1ZPNJDSLJL58bAGUTJ_m2g";
		String uuid = "db9b28c3-cc25-492e-8ebf-5e1d5f126ce6";
		String state = "test";

		var token = AuthorizationTokenDto.builder().accessToken(accessToken).refreshToken(refreshToken).build();
		var result = service.reissueToken(uuid, token, state);

		assertNotNull(result);
		System.out.println("access-token: " + result.getAccessToken());
		assertEquals(result.getDeviceId(), uuid);
		assertEquals(result.getState(), state);
	}

	@Test
	void validateToken() {
		String uuid = "db9b28c3-cc25-492e-8ebf-5e1d5f126ce6";
		String accessToken = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhY2Nlc3MtdG9rZW4iLCJleHAiOjE3MTE2ODcxODMsImlkIjoxLCJuaWNrbmFtZSI6InRlc3QiLCJzY29wZSI6Ik1FTUJFUiJ9.10I5pKg-OlzCVT5YlklXwRHHxXo4VCy7I_EkmNNhcgxEWzHmgXb-cszOl6Ze_2SCY8rnWHw8qDW-xR3VHJFe2A";
		String refreshToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyZWZyZXNoLXRva2VuIiwiZXhwIjoxNzEyMjA1NTgzLCJpZCI6MX0.EH7Y6vJPp4kxG-sFYAeES5umFNDqAFNqVBJ5zaJ4aBN7FYolGdgpRo_ciXwALcyU5CmWbe33S3B9GzAM2YKxMg";

		int memberId = 1;
		JwtScopeDto scope = JwtScopeDto.MEMBER;

		var access = AuthorizationTokenDto.builder().accessToken(accessToken).build();
		var refresh = AuthorizationTokenDto.builder().refreshToken(refreshToken).build();
		var accessResult = service.validateToken(accessToken);
		var accessResult2 = service.validateToken(uuid, access);
		var refreshResult = service.validateToken(uuid, refresh);
		assertNotNull(accessResult);
		assertNotNull(accessResult2);
		assertNotNull(refreshResult);
		assertEquals(memberId, accessResult.getMemberId());
		assertEquals(scope, accessResult.getScope());
		assertEquals(memberId, accessResult2.getMemberId());
		assertNull(refreshResult.getMemberNickname());
	}

	@Test
	void revokeToken() {
		String accessToken = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhY2Nlc3MtdG9rZW4iLCJleHAiOjE3MTE2ODc4MTQsImlkIjoxLCJuaWNrbmFtZSI6InRlc3QiLCJzY29wZSI6Ik1FTUJFUiJ9.EONh1UM7bkJWEYK63gANxsQuCPEpHSkqSDCtMH9bm9QxvbDBpbfCZ8uLh9TpJWCtF2ivXoKnkjsxUFaLpFKl9w";
		String refreshToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyZWZyZXNoLXRva2VuIiwiZXhwIjoxNzEyMjA2MjE0LCJpZCI6MX0.3HqzUG6dZTj9qKvrW3EnaEM0hi66FmuT1Val-wNW1btN1Bc4LrtSFpkrEPisMZOTgfEdU_zLTJ-eKxP36Bc0XQ";
		String uuid = "db9b28c3-cc25-492e-8ebf-5e1d5f126ce6";
		var token = AuthorizationTokenDto.builder().accessToken(accessToken).refreshToken(refreshToken).build();

		var result = service.revokeToken(uuid, token);
		assertTrue(result);
	}
}