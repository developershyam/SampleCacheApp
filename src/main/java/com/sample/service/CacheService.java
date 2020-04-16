package com.sample.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.sample.model.RedisModel;
import com.sample.repositories.RedisRepository;

@Service
public class CacheService {

	@Autowired
	private RedisRepository redisRepository;

	@CachePut(value = "RedisModel", key = "#model?.id")
	public RedisModel addModel(RedisModel model) {

		System.out.println("addModel..........");

		redisRepository.add(model.getId(), model);
		printAll();
		return model;
	}

	@Cacheable(value = "RedisModel", key = "#id")
	public RedisModel getModel(String id) {

		System.out.println("getModel..........");
		return (RedisModel) redisRepository.findById(id);
	}

	@CacheEvict(value = "RedisModel", key = "#id.toString()")
	public String delete(String id) {

		System.out.println("delete....");
		redisRepository.delete(id);
		return "Deleted";
	}

	@CacheEvict(value = "RedisModel", allEntries = true)
	public String delete() {

		System.out.println("delete....all");
		redisRepository.delete();
		return "Deleted All";
	}

	private void printAll() {

		System.out.println("********** Print All Data ****************");
		Map<Object, Object> all = redisRepository.findAll();
		Map<String, String> map = new HashMap<String, String>();
		for (Map.Entry<Object, Object> entry : all.entrySet()) {
			String key = (String) entry.getKey();
			map.put(key, all.get(key).toString());
			System.out.println("[key=" + key + "], value= [" + all.get(key).toString() + "]");
		}
		System.out.println("##########################################");
	}

	public List<RedisModel> getAll() {

		List<RedisModel> data = new ArrayList<>();
		Map<Object, Object> all = redisRepository.findAll();
		Map<String, String> map = new HashMap<String, String>();
		for (Map.Entry<Object, Object> entry : all.entrySet()) {
			String key = (String) entry.getKey();
			map.put(key, all.get(key).toString());
			data.add((RedisModel) all.get(key));
		}
		return data;
	}

}
