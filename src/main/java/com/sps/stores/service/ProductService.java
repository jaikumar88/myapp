package com.sps.stores.service;

import java.util.List;

import com.sps.stores.model.Product;

public interface ProductService {

	Product findById(int id);
	
	void saveProduct(Product product);
	
	void updateProduct(Product product);
	
	void deleteProductById(String id);

	List<Product> findAllProducts();

	boolean isProductUnique(String product); 

}
