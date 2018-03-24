package com.sps.stores.service;

import java.util.List;

import com.sps.stores.model.Activity;

public interface ActivtiesService {

	Activity findById(int id);
	
	Activity findByDate(String date);
	
	void saveActivity(Activity activity);
	
	void updateActivity(Activity activity);
	
	void deleteActivityByActivityId(String id);

	List<Activity> findAllActivities(); 
	
	List<Activity> findAllActivitiesByDate(String date); 
}
