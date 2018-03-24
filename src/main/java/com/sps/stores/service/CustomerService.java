package com.sps.stores.service;

import java.util.List;

import com.sps.stores.model.Customer;

public interface CustomerService {

	Customer findById(int id);
	
	Customer findByName(String name);
	
	void saveCustomer(Customer customer);
	
	void updateCustomer(Customer customer);
	
	void deleteCustomerById(String id);

	List<Customer> findAllCustomers(); 
	
	List<Customer> findAllCustomer(String location); 
}
