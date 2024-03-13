package com.githerbs.auth.domain.auth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.githerbs.auth.domain.auth.entity.JwtRedisEntity;

@Repository
public interface JwtRedisRepository extends CrudRepository<JwtRedisEntity, String> {
}
