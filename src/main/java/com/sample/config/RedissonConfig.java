package com.sample.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
@EnableCaching
public class RedissonConfig extends CachingConfigurerSupport {

	@Bean(destroyMethod = "shutdown")
	RedissonClient redisson(@Value("classpath:/redisson.yaml") Resource configFile) throws IOException{
		
		//Config config = new Config();
		//config.useSingleServer().setAddress("redis://127.0.0.1:6379");
		Config config = Config.fromYAML(configFile.getInputStream());
		return Redisson.create(config);
	}

	@Bean
	CacheManager cacheManager(RedissonClient redissonClient) {
		Map<String, CacheConfig> config = new HashMap<String, CacheConfig>();

		// create "testMap" cache with ttl = 24 minutes and maxIdleTime = 12 minutes
		config.put("testMap", new CacheConfig(24 * 60 * 1000, 12 * 60 * 1000));
		return new RedissonSpringCacheManager(redissonClient, config);
	}

	@Bean
	public RedissonConnectionFactory redissonConnectionFactory(RedissonClient redisson) {
		return new RedissonConnectionFactory(redisson);
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedissonClient redisson) {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redissonConnectionFactory(redisson));
		template.setKeySerializer(stringRedisSerializer());
		template.setHashKeySerializer(stringRedisSerializer());
		template.setValueSerializer(genericJackson2JsonRedisJsonSerializer());
		template.setHashValueSerializer(genericJackson2JsonRedisJsonSerializer());
		return template;
	}

	@Bean
	public StringRedisSerializer stringRedisSerializer() {
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		return stringRedisSerializer;
	}

	@Bean
	public GenericJackson2JsonRedisSerializer genericJackson2JsonRedisJsonSerializer() {
		GenericJackson2JsonRedisSerializer genericJackson2JsonRedisJsonSerializer = new GenericJackson2JsonRedisSerializer();
		return genericJackson2JsonRedisJsonSerializer;
	}

}
