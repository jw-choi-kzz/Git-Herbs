package com.happiness.githerbs.domain.auth.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happiness.githerbs.domain.auth.entity.JwtRedisEntity;
import com.happiness.githerbs.domain.auth.repository.JwtRedisRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {

	private final JwtRedisRepository repo;

	@Override
	public boolean setRefreshToken(int id, String refreshToken, String deviceId) {

		// 이미 존재하는 경우 삭제(user 쪽에서 이미 로그아웃 처리하고 넘겨주는 경우)
		repo.findById(deviceId).ifPresent(repo::delete);

		var entity = JwtRedisEntity.builder().id(deviceId).memberId(id).refreshToken(refreshToken).build();
		var result = repo.save(entity);

		return result.getId().equals(deviceId) && result.getRefreshToken().equals(refreshToken);
	}

	@Override
	public JwtRedisEntity getRefreshToken(String deviceId) {
		return repo.findById(deviceId).orElse(null);
	}

	@Override
	public boolean updateRefreshToken(int id, String refreshToken, String deviceId) {
		// 존재하지 않으면 false
		if (repo.findById(deviceId).isEmpty()) {
			return false;
		}
		var entity = repo.findById(deviceId).get();
		entity.setRefreshToken(refreshToken);
		var result = repo.save(entity);

		return result.getRefreshToken().equals(refreshToken);
	}

	@Override
	public boolean deleteRefreshToken(String deviceId) {
		repo.deleteById(deviceId);
		return repo.findById(deviceId).orElse(null) == null;
	}
}
