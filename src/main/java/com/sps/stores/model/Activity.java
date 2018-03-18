package com.sps.stores.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ACTIVITY")
public class Activity {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="CUST_ID",nullable=false)
	private int custId;
	
	@Column(name="CREATE_DATE",nullable=false)
	private Date activityCreateDate;
	
	private String activityType;
	
	private String memo;
	
	private String amount;
	
	
	
	
	
	

}
