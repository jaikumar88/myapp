package com.sps.stores.application;

import java.sql.Date;


/**
 * @author Jai1.Kumar
 *
 */
public interface AppUtil {

	public String calculateIntrestAsOfToday(String amount,String startDate,String rate);
	
	public double formatDouble(double value);
	
	public String dateToString(Date date);
	
	public String dateToString(java.util.Date date);
	
	public Date stringToDate(String date);
	
	public java.util.Date stringToUtilDate(String date);
	
	public String calculateIntrestBetween(String amount,String startDate,String intRate,String endDate);
}
