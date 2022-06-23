package com.wylee.proj;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;

import com.wylee.proj.entity.User;
import com.wylee.proj.repository.UserRedisRepository;
import com.wylee.proj.util.RedisUtil;

import lombok.RequiredArgsConstructor;

@SpringBootTest
class ProjApplicationTests {
	
	@Autowired
	private UserRedisRepository repo;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Autowired
	private RedisUtil redisUtil;
	
	@Test
	void contextLoads() {
		
	}
	
	void printRedis() {
		String value1 = redisUtil.getValue("key1");
		Set<String> value2 = redisUtil.getSet("key2");
		Map<String, String> value3 = redisUtil.getMap("key4");
		
		System.out.println("value1 : " + value1);
		System.out.println("value2 : " + value2);
		System.out.println("value3 : " + value3);
	}
	
	void opsForHash() {
		HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
		String key = "hashKey123";
		String hashKey = "hashKey";
		String hashValue = "hashValue";
		
		hashOperations.put(key, hashKey, hashValue);
		String value = hashOperations.get(key, hashKey);
		
		assertThat(hashValue).isEqualTo(value);
		
		Map<String, String> map = hashOperations.entries(key);
		assertThat(map.keySet()).containsExactly(hashKey);
		assertThat(map.values()).containsExactly(hashValue);
		
		assertThat(map.size()).isEqualTo(hashOperations.size(key));
	}
	
	void opsForSet() {
		SetOperations<String, String> valueOperations = redisTemplate.opsForSet();
		String key = "setKey123";
		
		valueOperations.add(key, "one", "two", "three");
		Set<String> value = valueOperations.members(key);
		
		assertThat(value).containsOnly("one", "two", "three");
		assertThat(value.size()).isEqualTo(4);
	}
	
	void opsForValue() {
		ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
		String key = "key123";
		String value = "value123";
		
		valueOperations.set(key, value);
		
		assertThat(value).isEqualTo(valueOperations.get(key));
	}
	
	void userTest() {
		User user = new User("wylee2", "1234");
		repo.save(user);
		Optional<User> findUser = repo.findById(user.getId());
		System.out.println("find user : " + findUser.get().toString());
		long cnt = repo.count();
		System.out.println("repo count : " + cnt);
		repo.delete(user);
	}

}
