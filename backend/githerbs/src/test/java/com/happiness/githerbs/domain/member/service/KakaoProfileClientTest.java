package com.happiness.githerbs.domain.member.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.happiness.githerbs.global.config.S3Uploader;

@SpringBootTest
class KakaoProfileClientTest {
	@Autowired
	private KakaoProfileClient profile;
	@Autowired
	private S3Uploader s3;

	@Test
	void userProfileClient() throws IOException {
		String url = "";
		String fileName = url.split("/")[url.split("/").length - 2];
		String extension = url.split("\\.")[url.split("\\.").length - 1];
		URI uri = URI.create(url);
		var res  = profile.userProfileClient(uri);
		assertNotNull(res);
		var in = res.body().asInputStream();
		var path = "C:\\Users\\User\\Documents\\" + fileName + "." + extension;
		var file = s3.convert(in, path);
		assertNotNull(file);
		assertTrue(file.isPresent());
		System.out.println(file.get().getName());
	}

}