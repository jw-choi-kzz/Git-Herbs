package com.githerbs.auth.domain.auth.service;

import com.githerbs.auth.domain.auth.entity.JwtRedisEntity;

public interface RedisService {
	boolean setRefreshToken(String id, String refreshToken, String deviceId);
	JwtRedisEntity getRefreshToken(String deviceId);
	JwtRedisEntity getRefreshTokenByMemberId(String memberId);
	boolean updateRefreshToken(String id, String refreshToken, String deviceId);
	boolean deleteRefreshToken(String deviceId);
}
