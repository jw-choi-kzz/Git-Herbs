package com.githerbs.auth.global.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "redis")
public class RedisClusterNodes {
	private RedisInstance master;
	private List<RedisInstance> nodes;
}


@Getter
@Setter
class RedisInstance {
	private String host;
	private int port;
}