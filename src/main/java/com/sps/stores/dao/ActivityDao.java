package com.sps.stores.dao;

import java.sql.Date;
import java.util.List;

import com.sps.stores.model.Activity;

public interface ActivityDao {

	Activity findById(int id);
	
	Activity findByDate(Date date);
	
	void save(Activity activity);
	
	void deleteById(String id);
	
	List<Activity> findAllActivities();
	
	List<Activity>  findAllActivitiesByDate(String date);
	
	void delete(Activity activity);
	
	List<Activity> findAllActivities(String location,String custId, String date);
}
