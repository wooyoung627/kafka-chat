package com.kafka.chat.util;

import java.util.Map;
import java.util.Set;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

//@Component
@RequiredArgsConstructor
public class RedisUtil {
	
	private final RedisTemplate<String, String> redisTemplate;
	
	public void save(String key, String value) {
		ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
		valueOperations.set(key, value);
	}
	
	public void save(String key, Set<String> value) {
		SetOperations<String, String> valueOperations = redisTemplate.opsForSet();
		valueOperations.add(key, value.toArray(new String[value.size()]));
	}
	
	public void save(String key, Map<String, String> value) {
		HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
		hashOperations.putAll(key, value);
	}
	
	public String getValue(String key) {
		ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
		return valueOperations.get(key);
	}
	
	public Set<String> getSet(String key){
		SetOperations<String, String> valueOperations = redisTemplate.opsForSet();
		return valueOperations.members(key);
	}
	
	public Map<String, String> getMap(String key){
		HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
		return hashOperations.entries(key);
	}
}
