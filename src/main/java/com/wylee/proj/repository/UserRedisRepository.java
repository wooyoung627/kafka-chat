package com.wylee.proj.repository;

import org.springframework.data.repository.CrudRepository;

import com.wylee.proj.entity.User;

public interface UserRedisRepository extends CrudRepository<User, String> {

}
