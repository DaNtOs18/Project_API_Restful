package com.api.project.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Item implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column(name = "item_code")
	private int itemCode;
	@Column
	private String description;
	@Column
	private float price;
	@OneToOne(cascade=CascadeType.REMOVE, fetch = FetchType.LAZY)
	@JoinColumn(name = "state", nullable = false)
	private ItemState state;
	/* Cambiada esta anotación por la que se encontraba en Supplier. Removido el Cascade para evitar el borrado de Supplier. */
	@ManyToMany( fetch = FetchType.EAGER)
	@JoinTable(name = "item_supplier", 
	joinColumns = @JoinColumn(name = "item_id"), 
	inverseJoinColumns = @JoinColumn(name = "supplier_id")
	)
	private List<Supplier> suppliers;
	@Column(name="prices_reduction")
	@OneToMany
	private List<PriceReduction> pricesReductions;
	@Column(name = "creation_at")
	private Date creationDate;
	@ManyToOne
	@JoinColumn(name = "created_by")
	private User createdBy;
	
	public Item() {
	}

	public int getId() {
		return id;
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
	
	public void setSupplier(Supplier supplier) {
        this.suppliers.add(supplier);
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
