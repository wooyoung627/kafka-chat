package com.kafka.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kafka.chat.domain.User;

@Repository
public interface UserDBRepository extends JpaRepository<User, String> {
}
