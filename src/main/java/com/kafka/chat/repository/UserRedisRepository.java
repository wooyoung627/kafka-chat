package com.kafka.chat.repository;

import org.springframework.data.repository.CrudRepository;

import com.kafka.chat.domain.User;

public interface UserRedisRepository extends CrudRepository<User, String> {

}
