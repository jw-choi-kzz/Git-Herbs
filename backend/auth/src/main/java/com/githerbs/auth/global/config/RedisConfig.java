package com.githerbs.auth.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.RedisStaticMasterReplicaConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisKeyValueAdapter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import io.lettuce.core.ReadFrom;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableRedisRepositories(enableKeyspaceEvents = RedisKeyValueAdapter.EnableKeyspaceEvents.ON_STARTUP)
@Configuration
public class RedisConfig {

	private final RedisClusterNodes nodes;
	@Value("${redis.password}")
	private String password;

	/**
	 * redis 연결 설정
	 * */
	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		/*redis cluster를 구성한 경우*/
		// LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder().readFrom(
		// 	ReadFrom.REPLICA_PREFERRED).build();
		// RedisStaticMasterReplicaConfiguration config = new RedisStaticMasterReplicaConfiguration(
		// 	nodes.getMaster().getHost(),
		// 	nodes.getMaster().getPort());
		// nodes.getNodes().forEach(node -> config.addNode(node.getHost(), node.getPort()));

		/*redis standalone을 구성한 경우*/
		LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder().build();
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(nodes.getMaster().getHost(), nodes.getMaster().getPort());

		config.setPassword(password);
		return new LettuceConnectionFactory(config, clientConfig);
	}

	@Bean
	public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
		return new GenericJackson2JsonRedisSerializer();
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate(
		RedisConnectionFactory factory,
		RedisSerializer<Object> springSessionDefaultRedisSerializer
	) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(factory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(springSessionDefaultRedisSerializer);
		return template;
	}

}
