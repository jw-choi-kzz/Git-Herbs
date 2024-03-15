package com.githerbs.auth.domain.auth.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.githerbs.auth.domain.auth.entity.JwtRedisEntity;

@Repository
public interface JwtRedisRepository extends CrudRepository<JwtRedisEntity, String> {
	Optional<JwtRedisEntity> findByMemberId(String memberId);
}
