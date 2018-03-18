package com.sps.stores.dao;

import java.util.List;

import com.sps.stores.model.Store;


public interface StoreDao {

	Store findById(int id);
	
	Store findByStoreId(String storeId);
	
	void save(Store store);
	
	void deleteByStoreId(String storeId);
	
	List<Store> findAllStores();
	
	List<Store>  findAllStoresByStoreId(String storeId);
	
	void delete(Store store);
	

}

