package com.sps.stores.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sps.stores.application.AppUtil;
import com.sps.stores.dao.location.LocationDao;
import com.sps.stores.model.Location;

@Service("locationService")
@Transactional
public class LocationServiceImpl implements LocationService {

	@Autowired
	LocationDao locationDao;
	
	@Autowired
	AppUtil appUtil;

	@Override
	public Location findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveLocation(Location location) {
		locationDao.save(location);
		
	}

	@Override
	public void updateLocation(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteLocationById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Location> findAllLocations() {
	
		return locationDao.findAllLocations();
	}

	@Override
	public boolean isLocationUnique(String location) {
		
		for(Location loc: locationDao.findAllLocations()){
			if(loc.getLocation().equalsIgnoreCase(location))
				return true;
		}
		return false;
	}
	
}
