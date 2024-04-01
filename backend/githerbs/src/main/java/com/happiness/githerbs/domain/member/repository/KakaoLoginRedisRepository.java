package com.happiness.githerbs.domain.member.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.happiness.githerbs.domain.member.entity.KakaoLoginRedisEntity;

@Repository
public interface KakaoLoginRedisRepository extends CrudRepository<KakaoLoginRedisEntity, String> {
}
