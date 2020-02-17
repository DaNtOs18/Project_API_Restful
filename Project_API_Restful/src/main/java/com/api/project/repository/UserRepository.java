package com.api.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.project.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
