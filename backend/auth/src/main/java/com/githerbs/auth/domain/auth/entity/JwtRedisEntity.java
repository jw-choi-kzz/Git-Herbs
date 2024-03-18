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
@RedisHash(value = "jwt", timeToLive = (60L * 60L * 24L * 7L))
public class JwtRedisEntity {
	@Id
	private String id;    // device_id
	// @Indexed
	private int memberId;    // member_id
	private String refreshToken;
}
