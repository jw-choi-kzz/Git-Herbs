package com.githerbs.auth.domain.auth.service;

import com.githerbs.auth.domain.auth.entity.JwtRedisEntity;

public interface RedisService {
	boolean setRefreshToken(String id, String refreshToken);
	JwtRedisEntity getRefreshToken(String id);
	boolean updateRefreshToken(String id, String refreshToken);
	boolean deleteRefreshToken(String id);
}
