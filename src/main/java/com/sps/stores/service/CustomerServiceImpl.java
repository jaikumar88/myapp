package com.sps.stores.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sps.stores.dao.CustomerDao;
import com.sps.stores.model.Customer;

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDao customerDao;
	
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
		// TODO Auto-generated method stub
		return customerDao.findAllCustomers();
	}

	@Override
	public List<Customer> findAllCustomer(String location) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}
