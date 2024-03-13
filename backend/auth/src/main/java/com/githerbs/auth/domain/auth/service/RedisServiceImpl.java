package com.githerbs.auth.domain.auth.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.githerbs.auth.domain.auth.entity.JwtRedisEntity;
import com.githerbs.auth.domain.auth.repository.JwtRedisRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {

	private final JwtRedisRepository repo;

	@Override
	public boolean setRefreshToken(String id, String refreshToken) {

		if(repo.findById(id).orElse(null) != null) return false;
		JwtRedisEntity entity = JwtRedisEntity.builder().id(id).refreshToken(refreshToken).build();
		JwtRedisEntity result = repo.save(entity);


		if(result.getId().equals(id) && result.getRefreshToken().equals(refreshToken)) return true;

		return false;
	}

	@Override
	public JwtRedisEntity getRefreshToken(String id) {
		return null;
	}

	@Override
	public boolean updateRefreshToken(String id, String refreshToken) {
		return false;
	}

	@Override
	public boolean deleteRefreshToken(String id) {
		return false;
	}
}
