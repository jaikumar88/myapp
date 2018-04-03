package com.sps.stores.dao;

import java.util.List;

import com.sps.stores.model.Location;

public interface LocationDao {

	Location findById(int id);
	
	void save(Location location);
	
	void deleteById(String id);
	
	List<Location> findAllLocations();
	
	
	void delete(Location location);
}
