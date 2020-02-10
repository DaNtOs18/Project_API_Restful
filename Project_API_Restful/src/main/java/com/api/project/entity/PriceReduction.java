package com.api.project.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="price_reduction")
public class PriceReduction implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable = false, nullable = false)
	private int id;
	@Column(name="reduced_price")
	private float reducedPrice;
	@Column(name="start_date")
	private Date startDate;
	@Column(name="end_date")
	
	private Date endDate;
	
	public PriceReduction() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getReducedPrice() {
		return reducedPrice;
	}

	public void setReducedPrice(float reducedPrice) {
		this.reducedPrice = reducedPrice;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
