package com.githerbs.auth.domain.auth.entity;

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
@RedisHash(value = "jwt", timeToLive = (60L * 60L * 24L * 30L))
public class JwtRedisEntity {
	@Id
	private String id;    // member_id
	private String refreshToken;
}
