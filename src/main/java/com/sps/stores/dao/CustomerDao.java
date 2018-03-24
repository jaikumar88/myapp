package com.sps.stores.dao;

import java.util.List;

import com.sps.stores.model.Customer;

public interface CustomerDao {

	Customer findById(int id);
	
	Customer findByName(String name);
	
	void save(Customer customer);
	
	void deleteById(String id);
	
	List<Customer> findAllCustomers();
	
	List<Customer>  findAllCustomersByLocation(String location);
	
	void delete(Customer customer);
}
