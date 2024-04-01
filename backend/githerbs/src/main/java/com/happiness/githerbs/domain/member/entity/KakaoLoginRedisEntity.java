package com.happiness.githerbs.domain.member.entity;

import org.springframework.data.redis.core.RedisHash;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "login", timeToLive = (60L * 60L * 24L * 7L))
public class KakaoLoginRedisEntity {
	@Id
	private String id;   // user_id:device_id
	private String kakaoAccessToken;
	private String kakaoIdToken;
	private String kakaoRefreshToken;
}
