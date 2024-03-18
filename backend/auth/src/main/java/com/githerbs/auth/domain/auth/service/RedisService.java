package com.githerbs.auth.domain.auth.service;

import com.githerbs.auth.domain.auth.entity.JwtRedisEntity;

public interface RedisService {
	boolean setRefreshToken(int id, String refreshToken, String deviceId);
	JwtRedisEntity getRefreshToken(String deviceId);
	boolean updateRefreshToken(int id, String refreshToken, String deviceId);
	boolean deleteRefreshToken(String deviceId);
}
