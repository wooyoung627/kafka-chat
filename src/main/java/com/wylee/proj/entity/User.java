package com.wylee.proj.entity;

import java.time.LocalDateTime;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;


//@RedisHash(value="user")
//@RedisHash(value="user", timeToLive=30)
@Data
@Entity
public class User {

	@Id
	private String id;
	private String password;
	private String nickname;
	
	public User() {}

	public User(String id, String password, String nickname) {
		this.id = id;
		this.password = password;
		this.nickname = nickname;
	}
}
