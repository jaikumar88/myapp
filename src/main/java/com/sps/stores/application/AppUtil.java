package com.sps.stores.application;

import java.util.List;

import com.sps.stores.model.Activity;

public interface AppUtil {

	public String calculateIntrestAsOfToday(String amount,String startDate,String rate);
	
	public double formatDouble(double value);
	
	public List<Activity> calculateAnyDueOnCustomer(int custId);
}
