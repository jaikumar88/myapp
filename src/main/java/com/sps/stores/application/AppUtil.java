package com.sps.stores.application;

import java.sql.Date;
import java.util.List;

import com.sps.stores.model.Activity;

public interface AppUtil {

	public String calculateIntrestAsOfToday(String amount,String startDate,String rate);
	
	public double formatDouble(double value);
	
	public List<Activity> calculateAnyDueOnCustomer(int custId);
	
	public String dateToString(Date date);
	
	public String dateToString(java.util.Date date);
	
	public Date stringToDate(String date);
	
	public java.util.Date stringToUtilDate(String date);
}
