package com.sps.stores.dao.customer;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sps.stores.dao.AbstractDao;
import com.sps.stores.model.Customer;

@Repository("customerDao")
public class CustomerDaoImpl extends AbstractDao<Integer, Customer> implements CustomerDao {

	@Override
	public Customer findById(int id) {
		return getByKey(id);
	}

	
	@Override
	public void save(Customer Customer) {
		persist(Customer);

	}

	
	

	@Override
	public void delete(Customer Customer) {
		delete(Customer);
	}

	@Override
	public void deleteById(String id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("Id", id));
		Customer Customer = (Customer)crit.uniqueResult();
		delete(Customer);
	}


	@Override
	public Customer findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Customer> findAllCustomers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("location"));
		List<Customer> customers = criteria.list();
		return customers;
		
	}


	@Override
	public List<Customer> findAllCustomersByLocation(String location) {
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("location"));
		criteria.add(Restrictions.eq("location", location));
		List<Customer> customers = criteria.list();
		return customers;
	}

}
