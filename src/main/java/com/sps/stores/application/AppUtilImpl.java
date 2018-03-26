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

/**
 * @author Jai1.Kumar
 *
 */
@Service("appUtil")
public class AppUtilImpl implements AppUtil{

		public String calculateIntrestAsOfToday(String amount,String startDate,String rate){
			
			double instAmt = 0.00;
			try {
				List<String> monthsAndDays = calculateMonthsAndDaysBetweenDates(startDate);
				if(!monthsAndDays.isEmpty()){
					if(monthsAndDays.get(0) != null){
						double amt = Double.parseDouble(amount);
						int months = Integer.parseInt(monthsAndDays.get(0));
						double rt = Double.parseDouble(rate);
						
						 instAmt += (amt*rt*months)/100;
						
					}
					if(monthsAndDays.get(1) != null){
						
						double amt = Double.parseDouble(amount);
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
		
		public List<String> calculateMonthsAndDaysBetweenDates(String startDate) throws ParseException{
			List<String> monthsAndDays = new ArrayList();
		    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
		    String CURRDATE    = startDate;
		     

		    Date startdate = formatter.parse(CURRDATE);
		    Date enddate   = new Date();

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

		    //System.out.println("diffMonth==="+diffMonth +" Month(s) and " + diffDay + " Day(s)");
		    monthsAndDays.add(String.valueOf(diffMonth));
		    monthsAndDays.add(String.valueOf(diffDay));
		    return monthsAndDays;
		}

	
}
