package com.sps.stores.dao.product;

import java.util.List;

import com.sps.stores.model.Product;

public interface ProductDao {

	Product findById(int id);
	
	void save(Product product);
	
	void deleteById(String id);
	
	List<Product> findAllProducts();
	
	
	void delete(Product product);
}
