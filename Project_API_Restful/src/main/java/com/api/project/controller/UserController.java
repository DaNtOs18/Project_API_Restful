package com.api.project.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.project.entity.Item;
import com.api.project.entity.User;
import com.api.project.repository.UserRepository;

@RestController
public class UserController {
	private final UserRepository userRepository;

	UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/user/{id}")
	  User getUser(@PathVariable int id) {
	    return userRepository.findById(id).get();
	  }
	
	@PostMapping("/user")
	void recordUser(@RequestBody User user) {
		userRepository.save(user); 
	}

	@PutMapping("/user/{id}")
	void updateUser(@RequestBody User user, @PathVariable int id) {
		User userToUpdate = userRepository.getOne(id);
		userToUpdate.setName(user.getName());
		userToUpdate.setPassword(user.getPassword());
		userRepository.save(userToUpdate);
	}

	@DeleteMapping("/user/{id}")
	void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
}
