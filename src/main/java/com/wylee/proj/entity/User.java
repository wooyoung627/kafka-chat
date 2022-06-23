package com.wylee.proj.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@RedisHash(value="user")
//@RedisHash(value="user", timeToLive=30)
public class User {
	@Id
	private String id;
	private String password;
	private LocalDateTime createdAt;
	
	public User() {}
	
	public User(String id, String password) {
		this.id = id;
		this.password = password;
		this.createdAt = LocalDateTime.now();
	}
}
