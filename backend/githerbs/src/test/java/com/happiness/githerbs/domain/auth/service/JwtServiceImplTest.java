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
		String accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhY2Nlc3MtdG9rZW4iLCJleHAiOjE3MTA5ODQwNTgsImlkIjoxLCJuaWNrbmFtZSI6InRlc3QiLCJzY29wZSI6Ik1FTUJFUiJ9.nDJ4AD6NDTldNYT3UxgEdXr7TDhLY5pDnkzLsZceZByZc6Vv14s1u3v-KNDGgxd4ekDfPWCXhnTJ5J3obTOdWg";
		String refreshToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyZWZyZXNoLXRva2VuIiwiZXhwIjoxNzExNTg4ODU3LCJpZCI6MX0.noGI269aypYd8LTDqmFFC3ZY4ozjyfVWF4B8PCxXmOud9qvqZAe8rgTy1MRrXp-qiWnYHghRPnLUNmveuA4oSA";
		String uuid = "209a4920-a142-4739-a4e5-ac83a2f3288e";
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
		String uuid = "209a4920-a142-4739-a4e5-ac83a2f3288e";
		String accessToken = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhY2Nlc3MtdG9rZW4iLCJleHAiOjE3MTEwNzA1ODIsImlkIjoxLCJuaWNrbmFtZSI6InRlc3QiLCJzY29wZSI6Ik1FTUJFUiJ9.A39IPNi_mjIyCUzL4dULh566OlCRAEtUXkcbCg8txEb8TVoDmYiB4CCLAVRYVqQSSzLGC1ux_g9x4Wf2qO3eyg";
		String refreshToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyZWZyZXNoLXRva2VuIiwiZXhwIjoxNzExNTg4OTgyLCJpZCI6MX0.laVMyfC2OR86x0Y7p8cqD6aGxnTug07PjNaTpejDje9MDBQA0-wokdDrssxyyUnXexkvtP1JtlcuOZwtQ9pYqg";

		int memberId = 1;
		JwtScopeDto scope = JwtScopeDto.MEMBER;

		var access = AuthorizationTokenDto.builder().accessToken(accessToken).build();
		var refresh = AuthorizationTokenDto.builder().refreshToken(refreshToken).build();
		var accessResult = service.validateToken(accessToken);
		var refreshResult = service.validateToken(uuid, refresh);
		assertNotNull(accessResult);
		assertNotNull(refreshResult);
		assertEquals(memberId, accessResult.getMemberId());
		assertEquals(scope, accessResult.getScope());
		assertNull(refreshResult.getMemberNickname());
	}

	@Test
	void revokeToken() {
		String uuid = "209a4920-a142-4739-a4e5-ac83a2f3288e";
		String accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhY2Nlc3MtdG9rZW4iLCJleHAiOjE3MTEwNzA1ODIsImlkIjoxLCJuaWNrbmFtZSI6InRlc3QiLCJzY29wZSI6Ik1FTUJFUiJ9.A39IPNi_mjIyCUzL4dULh566OlCRAEtUXkcbCg8txEb8TVoDmYiB4CCLAVRYVqQSSzLGC1ux_g9x4Wf2qO3eyg";
		String refreshToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyZWZyZXNoLXRva2VuIiwiZXhwIjoxNzExNTg4OTgyLCJpZCI6MX0.laVMyfC2OR86x0Y7p8cqD6aGxnTug07PjNaTpejDje9MDBQA0-wokdDrssxyyUnXexkvtP1JtlcuOZwtQ9pYqg";
		var token = AuthorizationTokenDto.builder().accessToken(accessToken).refreshToken(refreshToken).build();

		var result = service.revokeToken(uuid, token);
		assertTrue(result);
	}
}