package com.happiness.githerbs.domain.auth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.happiness.githerbs.domain.auth.entity.JwtRedisEntity;

@Repository
public interface JwtRedisRepository extends CrudRepository<JwtRedisEntity, String> {
	// Optional<JwtRedisEntity> findByMemberId(String memberId);
}
