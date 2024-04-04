package com.happiness.githerbs.domain.member.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "state", timeToLive = (60L * 5L))
public class StateRedisEntity {
	@Id
	private String state;
	private String redirectUrl;
}
