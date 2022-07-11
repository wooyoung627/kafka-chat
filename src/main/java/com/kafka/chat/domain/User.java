package com.kafka.chat.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;


//@RedisHash(value="user")
//@RedisHash(value="user", timeToLive=30)
//@Data
@Getter
@Setter
@ToString
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
	
	public Collection<? extends GrantedAuthority> getAuthorities(){
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority("ROLE_USER"));
		return roles;
	}
}
