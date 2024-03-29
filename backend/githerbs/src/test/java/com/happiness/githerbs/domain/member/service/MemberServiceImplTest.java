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
		var idToken = "eyJraWQiOiI5ZjI1MmRhZGQ1ZjIzM2Y5M2QyZmE1MjhkMTJmZWEiLCJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiJkYzVhOWU3NTFmZGE5YWM5MDgyYTc1ODMyOTIwZGY5NiIsInN1YiI6IjMzODgxMTQyODgiLCJhdXRoX3RpbWUiOjE3MTE2ODgzNTcsImlzcyI6Imh0dHBzOi8va2F1dGgua2FrYW8uY29tIiwiZXhwIjoxNzExNzA5OTU3LCJpYXQiOjE3MTE2ODgzNTd9.XmHyLGHV36pWeUkbJHm1flLwZozM3PSNeXZb8G6MgR35IscVS2wnLLCB8duiAA69Wb6IVkqC8qukecjN6QajK5mKXqPrYnJR0TvsjxJffnXSKLFcglmEDqJr7fP-EVVeMAF-0hdTckdLoTLpWo4i9LspzqkGbi468iJWNf6ZLvGuDpaZ_eaz838MJdsuWX0fqEZolsTppezj_JpUJG7E2oOlWapQbNQzaWnlNBmCkIBlQsLaVhZwkIqGGzAsnuz0p7_7ENr0uCPTtYbYTd9pYL8gGfSDb7G5g361EhdVQB5OKOWG78KxjeYzGAAi_jpi5qdxoHrxSE4Xo8PPb4PeQw";
		var token = service.decodeIdToken(idToken);

		assertEquals("https://kauth.kakao.com", token.iss());
	}
}