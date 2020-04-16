package com.sample.repositories;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisRepository {

	private static final String KEY_NAMESPACE = "RedisData";

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	private HashOperations hashOperations;

	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
	}

	public void add(final String key, final Object object) {
		hashOperations.put(KEY_NAMESPACE, key, object);
	}

	public void delete(final String key) {
		hashOperations.delete(KEY_NAMESPACE, key);
	}

	public void delete() {
		hashOperations.delete(KEY_NAMESPACE, hashOperations.entries(KEY_NAMESPACE).keySet().toArray());
	}

	public Object findById(final String key) {
		return hashOperations.get(KEY_NAMESPACE, key);
	}

	public Map<Object, Object> findAll() {
		return hashOperations.entries(KEY_NAMESPACE);
	}
}
