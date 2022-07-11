package com.kafka.chat;

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

import com.kafka.chat.domain.User;
import com.kafka.chat.repository.UserDBRepository;
import com.kafka.chat.repository.UserRedisRepository;
import com.kafka.chat.util.RedisUtil;

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
