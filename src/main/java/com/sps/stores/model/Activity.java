package com.sps.stores.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ACTIVITY")
public class Activity {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="CUST_ID",nullable=false)
	private int custId;
	
	@Column(name="CREATE_DATE",nullable=false)
	private String activityCreateDate;
	
	@Column(name="ACT_TYP",nullable=false)
	private String activityType;
	
	@Column(name="MEMO",nullable=true)
	private String memo;
	
	@Column(name="AMOUNT",nullable=false)
	private String amount;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CUST_ID",referencedColumnName = "CUST_ID", nullable = false, updatable = false, insertable = false)
	private Customer owner;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getActivityCreateDate() {
		return activityCreateDate;
	}

	public void setActivityCreateDate(String activityCreateDate) {
		this.activityCreateDate = activityCreateDate;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activityCreateDate == null) ? 0 : activityCreateDate.hashCode());
		result = prime * result + ((activityType == null) ? 0 : activityType.hashCode());
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + custId;
		result = prime * result + id;
		result = prime * result + ((memo == null) ? 0 : memo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (activityCreateDate == null) {
			if (other.activityCreateDate != null)
				return false;
		} else if (!activityCreateDate.equals(other.activityCreateDate))
			return false;
		if (activityType == null) {
			if (other.activityType != null)
				return false;
		} else if (!activityType.equals(other.activityType))
			return false;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (custId != other.custId)
			return false;
		if (id != other.id)
			return false;
		if (memo == null) {
			if (other.memo != null)
				return false;
		} else if (!memo.equals(other.memo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", custId=" + custId + ", activityCreateDate=" + activityCreateDate
				+ ", activityType=" + activityType + ", memo=" + memo + ", amount=" + amount + "]";
	}

	public Customer getOwner() {
		return owner;
	}

	public void setOwner(Customer owner) {
		this.owner = owner;
	}
	
	
	
	
	
	
	
	

}
