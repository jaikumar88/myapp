package com.sps.stores.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sps.stores.application.AppUtil;
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
			double dueAmount = 0.00;
			for(Activity activity:activityDao.findAllActivities("", String.valueOf(customer.getId()), ""))
			{
			if(activity.getActivityCreateDate() != null && activity.getAmount() != null && activity.getActivityType().equalsIgnoreCase("Payment"))
			{
				dueAmount += Double.parseDouble(activity.getAmount()) + Double.parseDouble(appUtil.calculateIntrestAsOfToday(activity.getAmount(), activity.getActivityCreateDate(), activity.getIntrestrate()));
			}
			}
			customer.setDueAmount(String.valueOf(dueAmount));
		}
		return listCust;
		
	}

	@Override
	public List<Customer> findAllCustomer(String location) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}
