package com.sps.stores.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sps.stores.application.AppUtil;
import com.sps.stores.dao.LocationDao;
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
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return locationDao.findAllLocations();
	}
	
}
