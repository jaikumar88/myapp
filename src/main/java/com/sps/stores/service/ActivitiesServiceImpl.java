package com.sps.stores.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sps.stores.dao.ActivityDao;
import com.sps.stores.model.Activity;

@Service("activityService")
@Transactional
public class ActivitiesServiceImpl implements ActivtiesService {

	@Autowired
	ActivityDao activityDao;
	
	@Override
	public Activity findById(int id) {
		
		return activityDao.findById(id);
	}

	@Override
	public Activity findByDate(String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveActivity(Activity activity) {
		activityDao.save(activity);

	}

	@Override
	public void updateActivity(Activity activity) {
		activityDao.save(activity);

	}

	@Override
	public void deleteActivityByActivityId(String id) {
		activityDao.deleteById(id);
	}

	@Override
	public List<Activity> findAllActivities() {
		return activityDao.findAllActivities();
	}

	@Override
	public List<Activity> findAllActivitiesByDate(String date) {
		
		return activityDao.findAllActivitiesByDate(date);
	}

}
