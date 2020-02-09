package com.api.project.entity;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User {

	@Id
	@Column
	private int id;
	@Column
	private String name;
	@Column
	private String password;
	
	public User() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
