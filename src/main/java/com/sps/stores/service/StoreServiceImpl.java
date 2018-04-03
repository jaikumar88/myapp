package com.sps.stores.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sps.stores.dao.store.StoreDao;
import com.sps.stores.model.Store;

@Service("storeService")
@Transactional
public class StoreServiceImpl  implements StoreService {

	@Autowired
	private StoreDao dao;

	@Override
	public Store findById(int id) {
		return dao.findById(id);
	}

	@Override
	public Store findByStoreId(String storeId) {
		Store user = dao.findByStoreId(storeId);
		return user;
	}

	@Override
	public void saveStore(Store store) {
		dao.save(store);

	}

	@Override
	public void updateStore(Store store) {
		Store entity = dao.findById(store.getId());
		if(entity!=null){
			entity.setStoreId(store.getStoreId());
			entity.setStoreName(store.getStoreName());
			entity.setAddress(store.getAddress());
			entity.setCountry(store.getCountry());
		}

	}

	@Override
	public void deleteStoreByStoreId(String storeId) {
		dao.deleteByStoreId(storeId);

	}

	@Override
	public List<Store> findAllStores() {
		return dao.findAllStores();
	}

	@Override
	public boolean isStoreUnique(Integer id, String storeId,String country) {

			boolean isExist = false;
			List<Store> stores = findAllStoresBySearch(storeId, country);
			for(Store entity: stores){
				isExist =  entity == null || ((id != null) && (entity.getId() == id && country == entity.getCountry()));
				if(isExist)
					break;
			}
			return isExist;
		
	}

	@Override
	public List<Store> findAllStoresBySearch(String storeId, String country) {
		return dao.findAllStoresByStoreId(storeId);
	}

	@Override
	public void deleteStoreByStoreIdAndCountry(String storeId, String country) {
		List<Store> stores = findAllStoresBySearch(storeId, country);
		for(Store entity: stores){
			if(entity.getStoreId().equalsIgnoreCase(storeId) && entity.getCountry().equalsIgnoreCase(country))
			dao.delete(entity);
		}
		
	}

	@Override
	public Store findByStoreIdAndCountry(String storeId, String country) {
		List<Store> stores = findAllStoresBySearch(storeId, country);
		Store store = null;
		for(Store entity: stores){
			if(entity.getStoreId().equalsIgnoreCase(storeId) && entity.getCountry().equalsIgnoreCase(country))
			store = entity;
		}
		return store;
	}

}
