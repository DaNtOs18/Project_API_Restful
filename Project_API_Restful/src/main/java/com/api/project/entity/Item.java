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

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "item")
public class Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column(name = "item_code", unique = true, nullable = false)
	private int itemCode;
	
	@Column(nullable = false)
	private String description;
	
	@Column
	private float price;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne(cascade=CascadeType.REMOVE, fetch = FetchType.LAZY)
	@JoinColumn(name = "state", nullable = false)
	private ItemState state;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "item_supplier", 
	joinColumns = @JoinColumn(name = "item_id"), 
	inverseJoinColumns = @JoinColumn(name = "supplier_id")
	)
	private List<Supplier> suppliers;
	
	@JsonManagedReference	
	@OneToMany(mappedBy="itemRelationated")
	private List<PriceReduction> pricesReductions;
	
	@Column(name = "creation_at", insertable = false, updatable = false, columnDefinition="DATE DEFAULT CURRENT_TIMESTAMP")
	private Date creationDate;
	
	@ManyToOne
	@JoinColumn(name = "created_by")
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

	@Override
	public String toString() {
		return "Item [id=" + id + ", itemCode=" + itemCode + ", description=" + description + ", price=" + price
				+ ", state=" + state + ", suppliers=" + suppliers + ", pricesReductions=" + pricesReductions
				+ ", creationDate=" + creationDate + ", createdBy=" + createdBy + "]";
	}
	
}
