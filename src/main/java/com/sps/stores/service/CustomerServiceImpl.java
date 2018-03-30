package com.sps.stores.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sps.stores.application.AppUtil;
import com.sps.stores.application.ApplicationConstants;
import com.sps.stores.dao.ActivityDao;
import com.sps.stores.dao.CustomerDao;
import com.sps.stores.model.Activity;
import com.sps.stores.model.Customer;

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDao customerDao;
	
	@Autowired
	ActivityDao activityDao;
	
	@Autowired
	AppUtil appUtil;
	
	@Override
	public Customer findById(int id) {
		
		return customerDao.findById(id);
	}

	@Override
	public Customer findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveCustomer(Customer customer) {
		customerDao.save(customer);
		
	}

	@Override
	public void updateCustomer(Customer customer) {
		customerDao.save(customer);
		
	}

	@Override
	public void deleteCustomerById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Customer> findAllCustomers() {
		
		List<Customer> listCust = customerDao.findAllCustomers();
		
		for(Customer customer:listCust){
			double dueAmount = calculateTotalDueAmount(activityDao.findAllActivities("", String.valueOf(customer.getId()), ""));
			customer.setDueAmount(String.valueOf(appUtil.formatDouble(dueAmount)));
		}
		return listCust;
		
	}
	
	/**
	 * @param custId
	 * @param listActivity
	 * @return
	 */
	private double calculateTotalDueAmount(List<Activity> listActivity) {
		double totInterest = 0.00;
		double totdueAmount  = 0.00;
		
			for(Activity activity:listActivity){
				if(activity.getStatus().equalsIgnoreCase(ApplicationConstants.OPEN.value())){
				if(activity.getActivityCreateDate() != null && activity.getAmount() != null && 
						(activity.getActivityType().equalsIgnoreCase(ApplicationConstants.PAYMENT.value()) || 
						activity.getActivityType().equalsIgnoreCase(ApplicationConstants.ADVANCE.value())))
				{
					String intrestAmt = appUtil.calculateIntrestAsOfToday(activity.getAmount(), activity.getActivityCreateDate(), activity.getIntrestrate());
					totInterest += Double.valueOf(intrestAmt);
					totdueAmount += Double.valueOf(intrestAmt) + Double.valueOf(activity.getAmount().equalsIgnoreCase("")?"0.00":activity.getAmount());
					activity.setIntrestAmount(intrestAmt);
					activity.setTotalIntrest(String.valueOf(appUtil.formatDouble(totInterest)));
					activity.setTotalAmount(String.valueOf(appUtil.formatDouble(totdueAmount)));
					}
				} else if(activity.getActivityType().equalsIgnoreCase(ApplicationConstants.RECEIVED.value())){
					String intrestAmt = appUtil.calculateIntrestAsOfToday(activity.getAmount(), activity.getActivityCreateDate(), activity.getIntrestrate());
					totInterest -= Double.valueOf(intrestAmt);
					totdueAmount -= Double.valueOf(intrestAmt) + Double.valueOf(activity.getAmount().equalsIgnoreCase("")?"0.00":activity.getAmount());
					activity.setIntrestAmount(intrestAmt);
					activity.setTotalIntrest(String.valueOf(appUtil.formatDouble(totInterest)));
					activity.setTotalAmount(String.valueOf(appUtil.formatDouble(totdueAmount)));
				}
			}
		return totdueAmount;
		
	}

	@Override
	public List<Customer> findAllCustomer(String location) {
		// TODO Auto-generated method stub
		return customerDao.findAllCustomersByLocation(location);
	}

	@Override
	public List<Customer> findAllCustomersList() {
		return customerDao.findAllCustomers();
	}

	

	
}
