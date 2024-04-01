package com.happiness.githerbs.domain.member.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.happiness.githerbs.domain.member.entity.StateRedisEntity;

@Repository
public interface StateRedisRepository  extends CrudRepository<StateRedisEntity, String> {
}
