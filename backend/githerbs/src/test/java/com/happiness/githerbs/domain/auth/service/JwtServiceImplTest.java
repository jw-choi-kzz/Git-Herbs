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
		assertEquals(result.getDeviceId(), uuid);
		assertEquals(result.getState(), state);
		System.out.println("device-id: " + result.getDeviceId());
		System.out.println("state: " + result.getState());
		System.out.println("access-token: " + result.getAccessToken());

	}

	@Test
	void reissueToken() {
	}

	@Test
	void validateToken() {
		String accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhY2Nlc3MtdG9rZW4iLCJleHAiOjE3MTEwMDIzMTUsImlkIjoxLCJuaWNrbmFtZSI6InRlc3QiLCJzY29wZSI6Ik1FTUJFUiJ9.x9zoRp9kj5lztXmCXpYx8iGnLNAmnSiWUBpawDGWhQgKbweUEdREn4SumnNcSkOrJ0H8Vmy810v5hkaX3z16jQ";
		String uuid = "33a1a97b-4fc7-4cc4-938d-3d0e3c6a109a";
		String refreshToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUMiJ9.eyJzdWIiOiJyZWZyZXNoLXRva2VuIiwiZXhwIjoxNzExNTIwNzE1LCJpZCI6MX0.JdYGP6T_64ZUmUsxNR4NCp2e2C1Pqp_jx5zp0rOijFZ8uUobBQEC518ETp_bzbmkryRuQX-ipWxJuEu-3X1S3Q";
		int memberId = 1;
		JwtScopeDto scope = JwtScopeDto.MEMBER;

		var access = AuthorizationTokenDto.builder().accessToken(accessToken).build();
		var refresh = AuthorizationTokenDto.builder().refreshToken(refreshToken).build();
		var accessResult = service.validateToken(uuid, access);
		var refreshResult = service.validateToken(uuid, refresh);
		assertNotNull(accessResult);
		assertNotNull(refreshResult);
		assertEquals(memberId, accessResult.getMemberId());
		assertEquals(scope, accessResult.getScope());
		assertNull(refreshResult.getMemberNickname());
	}

	@Test
	void revokeToken() {
		String uuid = "33a1a97b-4fc7-4cc4-938d-3d0e3c6a109a";
		String accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhY2Nlc3MtdG9rZW4iLCJleHAiOjE3MTEwMDIzMTUsImlkIjoxLCJuaWNrbmFtZSI6InRlc3QiLCJzY29wZSI6Ik1FTUJFUiJ9.x9zoRp9kj5lztXmCXpYx8iGnLNAmnSiWUBpawDGWhQgKbweUEdREn4SumnNcSkOrJ0H8Vmy810v5hkaX3z16jQ";
		String refreshToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUMiJ9.eyJzdWIiOiJyZWZyZXNoLXRva2VuIiwiZXhwIjoxNzExNTIwNzE1LCJpZCI6MX0.JdYGP6T_64ZUmUsxNR4NCp2e2C1Pqp_jx5zp0rOijFZ8uUobBQEC518ETp_bzbmkryRuQX-ipWxJuEu-3X1S3Q";
		var token = AuthorizationTokenDto.builder().accessToken(accessToken).refreshToken(refreshToken).build();

		var result = service.revokeToken(uuid, token);
		assertTrue(result);
	}
}