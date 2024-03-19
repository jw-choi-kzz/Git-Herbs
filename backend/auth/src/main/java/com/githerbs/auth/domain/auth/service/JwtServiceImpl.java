package com.githerbs.auth.domain.auth.service;

import java.util.*;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.githerbs.auth.domain.auth.dto.common.MemberInfoDto;
import com.githerbs.auth.domain.auth.dto.common.AuthorizationTokenDto;
import com.githerbs.auth.domain.auth.dto.response.JwtExtrationResponseDto;
import com.githerbs.auth.domain.auth.dto.response.JwtResponseDto;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService{

	@Value("${spring.jwt.secret}")
	private String secretKey;
	@Value("${spring.jwt.access-token-validity-in-seconds}")
	private long accessTokenValidTime;
	@Value("${spring.jwt.refresh-token-validity-in-seconds}")
	private long refreshTokenValidTime;

	private final RedisService service;


	@Override
	@Transactional
	public JwtResponseDto createToken(String deviceId, MemberInfoDto memberInfo, String state) {

		long now = System.currentTimeMillis();
		var refreshKey = Jwts.SIG.HS512.key().build();
		if(deviceId == null || deviceId.trim().isEmpty()) {
			deviceId = UUID.randomUUID().toString();
		}
		String accessToken = Jwts.builder()
			.header()
			.add("typ", "JWT")
			.and()
			.subject("access-token")
			.expiration(new Date(now + 1000 * accessTokenValidTime))
			.claim("id", memberInfo.getMemberId())
			.claim("nickname", memberInfo.getMemberNickname())
			.claim("scope", memberInfo.getScope())
			.signWith(Keys.password(secretKey.toCharArray()), Jwts.SIG.HS512)
			.compact();
		String refreshToken = Jwts.builder()
			.header()
			.add("typ", "JWT")
			.and()
			.subject("refresh-token")
			.expiration(new Date(now + 1000 * refreshTokenValidTime))
			.claim("id", memberInfo.getMemberId())
			.signWith(refreshKey)
			.compact();

		var result = JwtResponseDto.builder().accessToken(accessToken).refreshToken(refreshToken).state(state).deviceId(deviceId).build();
		service.setRefreshToken(memberInfo.getMemberId(), refreshToken, deviceId);
		return result;
	}

	@Override
	public JwtResponseDto reissueToken(String deviceId, AuthorizationTokenDto token, String state) {
		return null;
	}

	@Override
	public JwtExtrationResponseDto validateToken(String deviceId, AuthorizationTokenDto token) {
		return null;
	}

	@Override
	public boolean revokeToken(String deviceId, AuthorizationTokenDto token) {
		return false;
	}
}
