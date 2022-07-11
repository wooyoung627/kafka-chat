package com.wylee.proj;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.wylee.proj.domain.User;
import com.wylee.proj.repository.UserDBRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;

import com.wylee.proj.repository.UserRedisRepository;
import com.wylee.proj.util.RedisUtil;

@SpringBootTest
class ProjApplicationTests {
	@Autowired
	private UserDBRepository userRepo;

	@Test
	void insertUser(){
//		User user = new User("id", "pw", "nick");
//		userRepo.save(user);
		
		System.out.println("TEST");
	}
	
	
}
