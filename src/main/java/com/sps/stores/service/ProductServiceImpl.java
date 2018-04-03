package com.sps.stores.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sps.stores.application.AppUtil;
import com.sps.stores.dao.product.ProductDao;
import com.sps.stores.model.Product;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;
	
	@Autowired
	AppUtil appUtil;

	@Override
	public Product findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveProduct(Product product) {
		productDao.save(product);
		
	}

	@Override
	public void updateProduct(Product product) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteProductById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Product> findAllProducts() {
	
		return productDao.findAllProducts();
	}

	@Override
	public boolean isProductUnique(String product) {
		
		for(Product loc: productDao.findAllProducts()){
			if(loc.getName().equalsIgnoreCase(product))
				return true;
		}
		return false;
	}
	
}
