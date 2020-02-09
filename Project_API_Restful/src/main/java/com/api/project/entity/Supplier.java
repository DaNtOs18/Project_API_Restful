package com.api.project.entity;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="supplier")
public class Supplier {

	@Id
	@Column
	private int id;
	@Column
	private String name;
	@Column
	private String country;
	@ManyToMany
	@JoinTable(name = "item_supplier", 
	joinColumns = @JoinColumn(name = "item_id"), 
	inverseJoinColumns = @JoinColumn(name = "supplier_id")
	)
	private List<Item> items;
	
	public Supplier() {
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
		return country;
	}

	public void setPassword(String password) {
		this.country = password;
	}
	
}
