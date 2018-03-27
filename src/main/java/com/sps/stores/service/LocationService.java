package com.sps.stores.service;

import java.util.List;

import com.sps.stores.model.Location;

public interface LocationService {

	Location findById(int id);
	
	void saveLocation(Location location);
	
	void updateLocation(Location location);
	
	void deleteLocationById(String id);

	List<Location> findAllLocations();

	boolean isLocationUnique(String location); 

}
