package com.api.project.entity;

import javax.persistence.*;

@Entity
@Table(name="item_state")
public class ItemState {

	@Id
	@Column
	private int id;
	@Column(name = "is_active")
	private boolean isActive;
	//@Column(name = "changed_by")
	@ManyToOne
	@JoinColumn(name = "changed_by", nullable=true)
	private User changedBy;
	@Column
	private String reason;

	public ItemState() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public User getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(User changedBy) {
		this.changedBy = changedBy;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
