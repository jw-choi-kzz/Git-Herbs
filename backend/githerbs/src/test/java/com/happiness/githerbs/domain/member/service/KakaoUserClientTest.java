package com.happiness.githerbs.domain.member.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.happiness.githerbs.domain.member.dto.request.KakaoUserInfoRequestDto;

@SpringBootTest
class KakaoUserClientTest {
	@Autowired
	private KakaoUserClient client;
	@Test
	void logoutClient() {
	}

	@Test
	void userInfoClient() {
		var accessToken = "Bearer 9VoWLrmxB19OuqhJ3crf6vWZIkt2GFJ5NGQKKcleAAABjokJd8FHueF-5ScOZw";
		var param = KakaoUserInfoRequestDto.builder().secureResource(true).build();
		var result = client.userInfoClient(accessToken, param);

		assertNotNull(result);
		System.out.println(result);
		assertEquals("이병창",  result.properties().nickname());
	}
}