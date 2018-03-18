package com.sps.stores.service;

import java.util.List;

import com.sps.stores.model.Store;


public interface StoreService {
	
	Store findById(int id);
	
	Store findByStoreId(String storeId);
	Store findByStoreIdAndCountry(String storeId,String country);
	void saveStore(Store store);
	
	void updateStore(Store store);
	
	void deleteStoreByStoreId(String storeId);
	
	void deleteStoreByStoreIdAndCountry(String storeId,String country);

	List<Store> findAllStores(); 
	
	List<Store> findAllStoresBySearch(String storeId,String country);
	
	
	boolean isStoreUnique(Integer id, String storeId,String country);
	

}