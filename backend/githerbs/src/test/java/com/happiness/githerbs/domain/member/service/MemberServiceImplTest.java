package com.happiness.githerbs.domain.member.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceImplTest {
	@Autowired
	MemberServiceImpl service;



	@Test
	void loginService() {
	}

	@Test
	void tokenService() {
	}

	@Test
	void decodeIdToken() {
		var idToken = "";
		var token = service.decodeIdToken(idToken);

		assertEquals("", token.iss());
	}

	@Test
	void uploadProfile() {
		var profileUrl = "";
		var kakaoId = 123456789L;
		var path = service.uploadProfile(profileUrl, kakaoId);
		System.out.println("path: " + path);
		assertNotNull(path);
		assertNotEquals("", path);
		assertTrue(path.contains(""));

	}
}