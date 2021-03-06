package com.sps.stores.dao.product;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.sps.stores.dao.AbstractDao;
import com.sps.stores.model.Product;

@Repository("productDao")
public class ProductDaoImpl extends AbstractDao<Integer, Product> implements ProductDao {

	@Override
	public Product findById(int id) {
		return getByKey(id);
	}

	@Override
	public void save(Product product) {
		persist(product);
		
	}

	@Override
	public void deleteById(String id) {
		deleteById(id);
		
	}

	@Override
	public List<Product> findAllProducts() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Product> products = criteria.list();
		return products;
		
	}

	@Override
	public Product findByProductType(String type) {
		
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("name"));
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Product> products = criteria.list();
		
		return products.get(0);
	}

	

}
