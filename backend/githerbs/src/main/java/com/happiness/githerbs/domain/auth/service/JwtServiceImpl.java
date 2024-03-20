package com.happiness.githerbs.domain.auth.service;

import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.happiness.githerbs.domain.auth.dto.common.AuthorizationTokenDto;
import com.happiness.githerbs.domain.auth.dto.common.JwtScopeDto;
import com.happiness.githerbs.domain.auth.dto.common.MemberInfoDto;
import com.happiness.githerbs.domain.auth.dto.response.JwtExtrationResponseDto;
import com.happiness.githerbs.domain.auth.dto.response.JwtResponseDto;
import com.happiness.githerbs.global.common.code.ErrorCode;
import com.happiness.githerbs.global.common.exception.BaseException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.RequiredTypeException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
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

		if(deviceId == null || deviceId.trim().isEmpty()) {
			deviceId = UUID.randomUUID().toString();
		}
		var token = create(memberInfo);

		var result = JwtResponseDto.builder().accessToken(token.getAccessToken()).refreshToken(token.getRefreshToken()).state(state).deviceId(deviceId).build();
		service.setRefreshToken(memberInfo.getMemberId(), token.getRefreshToken(), deviceId);
		return result;
	}

	@Override
	@Transactional
	public JwtResponseDto reissueToken(String deviceId, AuthorizationTokenDto token, String state) {
		Claims claims = null;
		try {
			claims = getClaims(token.getAccessToken());
		}
		catch (ExpiredJwtException e) {
			claims = e.getClaims();
		}
		catch(MalformedJwtException | io.jsonwebtoken.security.SecurityException e) {
			log.error("MalformedJwtException", e);
			throw new BaseException("토큰 형식이 맞지 않습니다", ErrorCode.SECURITY_TOKEN_ERROR);
		}
		catch(UnsupportedJwtException e) {
			log.error("UnsupportedJwtException", e);
			throw new BaseException("지원하지 않는 토큰입니다", ErrorCode.UNSUPPORTED_TOKEN_ERROR);
		}
		catch(RequiredTypeException e) {
			log.error("RequiredTypeException", e);
			throw new BaseException("토큰 변환에 실패했습니다", ErrorCode.INTERNAL_SERVER_ERROR);
		}
		catch(Exception e) {
			log.error("Exception", e);
			throw new BaseException("적절하지 않은 토큰입니다", ErrorCode.WRONG_TOKEN_ERROR);
		}
		var redisObject = service.getRefreshToken(deviceId);

		if(!token.getRefreshToken().equals(redisObject.getRefreshToken())) throw new BaseException("사용자의 토큰이 아닙니다", ErrorCode.NOT_MATCH_TOKEN_ERROR);
		//TODO: 토큰 create 후 repo에 update

		return null;
	}

	@Override
	public JwtExtrationResponseDto validateToken(String deviceId, AuthorizationTokenDto token) {
		String accessToken = token.getAccessToken();
		if(accessToken != null && !accessToken.trim().isEmpty())  return validateToken(accessToken);
		if(token.getRefreshToken() != null && !token.getRefreshToken().trim().isEmpty()) {
			String refreshToken = token.getRefreshToken();
			if(refreshToken.equals(service.getRefreshToken(deviceId).getRefreshToken())) return new JwtExtrationResponseDto();
			else throw new BaseException("유효하지 않은 토큰입니다", ErrorCode.WRONG_TOKEN_ERROR);
		}
		else {
			throw new BaseException("유효하지 않은 토큰입니다", ErrorCode.WRONG_TOKEN_ERROR);
		}
	}

	@Override
	public JwtExtrationResponseDto validateToken(String accessToken) {
		try {
			var claims = getClaims(accessToken);
			Double memberDoubleId = claims.get("id", Double.class);
			int memberId = memberDoubleId.intValue();
			String memberNickname = claims.get("nickname", String.class);
			String scopeString = claims.get("scope", String.class);
			Gson gson = new GsonBuilder().create();
			JwtScopeDto scope = gson.fromJson(scopeString, JwtScopeDto.class);
			return JwtExtrationResponseDto.builder().memberId(memberId).memberNickname(memberNickname).scope(scope).build();
		}
		catch (ExpiredJwtException e) {
			log.error("ExpiredJwtException ", e);
			throw new BaseException("만료된 토큰입니다", ErrorCode.EXPIRED_TOKEN_ERROR);
		}
		catch(MalformedJwtException | io.jsonwebtoken.security.SecurityException e) {
			log.error("MalformedJwtException", e);
			throw new BaseException("토큰 형식이 맞지 않습니다", ErrorCode.SECURITY_TOKEN_ERROR);
		}
		catch(UnsupportedJwtException e) {
			log.error("UnsupportedJwtException", e);
			throw new BaseException("지원하지 않는 토큰입니다", ErrorCode.UNSUPPORTED_TOKEN_ERROR);
		}
		catch(RequiredTypeException e) {
			log.error("RequiredTypeException", e);
			throw new BaseException("토큰 변환에 실패했습니다", ErrorCode.INTERNAL_SERVER_ERROR);
		}
		catch(Exception e) {
			log.error("Exception", e);
			throw new BaseException("적절하지 않은 토큰입니다", ErrorCode.WRONG_TOKEN_ERROR);
		}

	}

	@Override
	@Transactional
	public boolean revokeToken(String deviceId, AuthorizationTokenDto token) {
		var result = validateToken(deviceId, token);
		if(result != null) {
			service.deleteRefreshToken(deviceId);
			return true;
		}
		else return false;
	}

	@Override
	public Claims getClaims(String accessToken) {
		SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
		var jwt = Jwts.parser().verifyWith(key).build().parseSignedClaims(accessToken);
		return jwt.getPayload();
	}

	@Override
	public AuthorizationTokenDto create(MemberInfoDto memberInfo) {
		long now = System.currentTimeMillis();
		var refreshKey = Jwts.SIG.HS512.key().build();
		SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
		String accessToken = Jwts.builder()
			.header()
			.add("typ", "JWT")
			.and()
			.subject("access-token")
			.expiration(new Date(now + 1000 * accessTokenValidTime))
			.claim("id", memberInfo.getMemberId())
			.claim("nickname", memberInfo.getMemberNickname())
			.claim("scope", memberInfo.getScope())
			.signWith(key, Jwts.SIG.HS512)
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
		return AuthorizationTokenDto.builder().accessToken(accessToken).refreshToken(refreshToken).build();
	}
}
