package com.api.project.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "item")
public class Item {

	@Id
	@Column
	private int id;
	@Column(name = "item_code")
	private int itemCode;
	@Column
	private String description;
	@Column
	private float price;
	//@Column
	@OneToOne
	@JoinColumn(name = "state", nullable = false)
	private ItemState state;
	@ManyToMany(mappedBy = "items")
	private List<Supplier> suppliers;
	@Column(name="prices_reduction")
	@OneToMany
	private List<PriceReduction> pricesReductions;
	@Column(name = "creation_at")
	private Date creationDate;
	//@Column(name = "created_by")
	@OneToOne
	@JoinColumn(name = "created_by", nullable = false)
	private User createdBy;
	
	public Item() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getItemCode() {
		return itemCode;
	}

	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public ItemState getState() {
		return state;
	}

	public void setState(ItemState state) {
		this.state = state;
	}

	public List<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(List<Supplier> suppliers) {
		this.suppliers = suppliers;
	}

	public List<PriceReduction> getPricesReductions() {
		return pricesReductions;
	}

	public void setPricesReductions(List<PriceReduction> pricesReductions) {
		this.pricesReductions = pricesReductions;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	
}
