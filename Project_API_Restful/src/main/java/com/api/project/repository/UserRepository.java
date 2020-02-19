package com.api.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.project.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	@Query("FROM user WHERE name = ?1 AND password = ?2")
	User findUser(String user, String password);
}
