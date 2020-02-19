package com.api.project.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.project.entity.User;
import com.api.project.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@RestController
public class UserController {
	private final UserRepository userRepository;

	UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@PostMapping("login")
	public User login(@RequestParam("user") String username, @RequestParam("password") String pwd) {
		User userDB = userCredentials(username,pwd);
		User user = new User();
		if(userDB != null) {
			String token = getJWTToken(username);
			user.setId(userDB.getId());
			user.setName(username);
			user.setToken(token);
		}		
		return user;	
	}
	
	/*
	@RequestMapping(value="/", produces = "text/html")
	public String welcome() {
		return "login.html";
	}
	*/
	

	public User userCredentials (String name, String password) {
		return userRepository.findUser(name, password);
	}
	
	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");

		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
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
