package com.wylee.proj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wylee.proj.domain.User;

@Repository
public interface UserDBRepository extends JpaRepository<User, String> {
}
