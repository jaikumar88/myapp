/**
 * 
 */
package com.sps.stores.application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sps.stores.model.Activity;

/**
 * @author Jai1.Kumar
 *
 */
@Service("appUtil")
@Transactional
public class AppUtilImpl implements AppUtil{

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	

	
		@Override
		public String calculateIntrestAsOfToday(String amount,String startDate,String intRate){
			
			double instAmt = 0.00;
			String inputAmt = amount.equalsIgnoreCase("")?"0.00":amount;
			String rate = intRate.equalsIgnoreCase("")?"0.00":intRate;
			try {
				List<String> monthsAndDays = calculateMonthsAndDaysBetweenDates(startDate,format.format(new Date()));
				if(!monthsAndDays.isEmpty()){
					if(monthsAndDays.get(0) != null){
						double amt = Double.parseDouble(inputAmt);
						int months = Integer.parseInt(monthsAndDays.get(0));
						double rt = Double.parseDouble(rate);
						
						 instAmt += (amt*rt*months)/100;
						
					}
					if(monthsAndDays.get(1) != null){
						
						double amt = Double.parseDouble(inputAmt);
						int days = Integer.parseInt(monthsAndDays.get(1));
						double rt = Double.parseDouble(rate);
						Date today = new Date();
						Calendar c = Calendar.getInstance();
						c.setTime(today);
						System.out.println("No of days in this months "+c.getActualMaximum(Calendar.DAY_OF_MONTH));
						
						 instAmt += (amt*rt*days)/(100*c.getActualMaximum(Calendar.DAY_OF_MONTH));
					}
				}
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
			instAmt =(double) Math.round(instAmt*100)/100;
			return String.valueOf(instAmt);
		}
		
@Override
public String calculateIntrestBetween(String amount,String startDate,String intRate,String endDate){
			
			double instAmt = 0.00;
			String inputAmt = amount.equalsIgnoreCase("")?"0.00":amount;
			String rate = intRate.equalsIgnoreCase("")?"0.00":intRate;
			try {
				List<String> monthsAndDays = calculateMonthsAndDaysBetweenDates(startDate,endDate);
				if(!monthsAndDays.isEmpty()){
					if(monthsAndDays.get(0) != null){
						double amt = Double.parseDouble(inputAmt);
						int months = Integer.parseInt(monthsAndDays.get(0));
						double rt = Double.parseDouble(rate);
						
						 instAmt += (amt*rt*months)/100;
						
					}
					if(monthsAndDays.get(1) != null){
						
						double amt = Double.parseDouble(inputAmt);
						int days = Integer.parseInt(monthsAndDays.get(1));
						double rt = Double.parseDouble(rate);
						Date today = new Date();
						Calendar c = Calendar.getInstance();
						c.setTime(today);
						 instAmt += (amt*rt*days)/(100*c.getActualMaximum(Calendar.DAY_OF_MONTH));
					}
				}
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
			instAmt =(double) Math.round(instAmt*100)/100;
			return String.valueOf(instAmt);
		}
		
		public List<String> calculateMonthsAndDaysBetweenDates(String startDate,String endDate) throws ParseException{
			List<String> monthsAndDays = new ArrayList();
		    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
		    String CURRDATE    = startDate;
		     

		    Date startdate = formatter.parse(CURRDATE);
		    Date enddate   = formatter.parse(endDate);

		    Calendar startCalendar = new GregorianCalendar();
		    startCalendar.setTime(startdate);

		    Calendar endCalendar = new GregorianCalendar();
		    endCalendar.setTime(enddate);

		    int monthCount = 0;
		    int firstDayInFirstMonth = startCalendar.get(Calendar.DAY_OF_MONTH);
		    startCalendar.set(Calendar.DAY_OF_MONTH, 1);
		    endCalendar.add(Calendar.DAY_OF_YEAR, -firstDayInFirstMonth+1);

		    while (!startCalendar.after(endCalendar)) {     
		        startCalendar.add(Calendar.MONTH, 1);
		        ++monthCount;
		    }

		    startCalendar.add(Calendar.MONTH, -1); --monthCount;
		    int remainingDays = 0;
		    while (!startCalendar.after(endCalendar)) {
		        startCalendar.add(Calendar.DAY_OF_YEAR, 1);
		        ++remainingDays;
		    }

		    startCalendar.add(Calendar.DAY_OF_YEAR, -1);
		    --remainingDays;

		    int lastMonthMaxDays = endCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		    if (remainingDays >= lastMonthMaxDays) {
		        ++monthCount;
		        remainingDays -= lastMonthMaxDays;
		    }

		    int diffMonth = monthCount; 
		    int diffDay = remainingDays; 

		    monthsAndDays.add(String.valueOf(diffMonth));
		    monthsAndDays.add(String.valueOf(diffDay));
		    return monthsAndDays;
		}

		@Override
		public double formatDouble(double value) {
			
			double retValue =(double) Math.round(value*100)/100;
			return retValue;
		}

	
		
		private List<Activity> calculateTotalAndDueAmount(List<Activity> listActivity) {
			double totInterest = 0.00;
			double totdueAmount  = 0.00;
			
				for(Activity activity:listActivity){
					if(activity.getStatus().equalsIgnoreCase(ApplicationConstants.OPEN.value())){
					if(activity.getActivityCreateDate() != null && activity.getAmount() != null && 
							(activity.getActivityType().equalsIgnoreCase(ApplicationConstants.PAYMENT.value()) || 
							activity.getActivityType().equalsIgnoreCase(ApplicationConstants.ADVANCE.value())))
					{
						String intrestAmt = this.calculateIntrestAsOfToday(activity.getAmount(), activity.getActivityCreateDate(), activity.getIntrestrate());
						totInterest += Double.valueOf(intrestAmt);
						totdueAmount += Double.valueOf(intrestAmt) + Double.valueOf(activity.getAmount().equalsIgnoreCase("")?"0.00":activity.getAmount());
						activity.setIntrestAmount(intrestAmt);
						activity.setTotalIntrest(String.valueOf(this.formatDouble(totInterest)));
						activity.setTotalAmount(String.valueOf(this.formatDouble(totdueAmount)));
						}
					} else if(activity.getActivityType().equalsIgnoreCase(ApplicationConstants.RECEIVED.value())){
						String intrestAmt = this.calculateIntrestAsOfToday(activity.getAmount(), activity.getActivityCreateDate(), activity.getIntrestrate());
						totInterest -= Double.valueOf(intrestAmt);
						totdueAmount -= Double.valueOf(intrestAmt) + Double.valueOf(activity.getAmount().equalsIgnoreCase("")?"0.00":activity.getAmount());
						activity.setIntrestAmount(intrestAmt);
						activity.setTotalIntrest(String.valueOf(this.formatDouble(totInterest)));
						activity.setTotalAmount(String.valueOf(this.formatDouble(totdueAmount)));
					}
				}
			return listActivity;
			
		}

		@Override
		public String dateToString(java.sql.Date date) {
			return format.format(date);
		}

		@Override
		public java.sql.Date stringToDate(String date) {
			java.sql.Date dt = null;
			if(date!=null && !"".equalsIgnoreCase(date))
				dt = java.sql.Date.valueOf(date);
			return dt;
		}

		@Override
		public Date stringToUtilDate(String date) {
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date dt = null;
			if(date!=null && !"".equalsIgnoreCase(date))
				date += " 00:00:00";
				try {
					dt = ft.parse(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			return dt;
		}
		@Override
		public String dateToString(Date date) {
			return format.format(date);
		}

	
}
