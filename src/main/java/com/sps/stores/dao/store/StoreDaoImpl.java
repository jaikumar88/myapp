package com.sps.stores.dao.store;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sps.stores.dao.AbstractDao;
import com.sps.stores.model.Store;

@Repository("storeDao")
public class StoreDaoImpl extends AbstractDao<Integer, Store> implements StoreDao {

	static final Logger logger = LoggerFactory.getLogger(StoreDaoImpl.class);
	
	@Override
	public Store findById(int id) {
		
		return getByKey(id);

	}

	@Override
	public Store findByStoreId(String storeId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("storeId", storeId));
		Store store = (Store)crit.uniqueResult();
		return store;
	}

	@Override
	public void save(Store store) {
		persist(store);

	}

	@Override
	public void deleteByStoreId(String storeId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("storeId", storeId));
		Store store = (Store)crit.uniqueResult();
		delete(store);
	}

	@SuppressWarnings("unchecked")
	public List<Store> findAllStores() {
		
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("country"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Store> stores = (List<Store>) criteria.list();
		return stores;
	}
	
	@SuppressWarnings("unchecked")
	public List<Store> findAllStoresByStoreId(String storeId) {
		
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("country"));
		criteria.add(Restrictions.eq("storeId", storeId));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Store> stores = (List<Store>) criteria.list();
		return stores;
	}

}
