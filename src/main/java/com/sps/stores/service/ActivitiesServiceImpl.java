package com.sps.stores.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sps.stores.application.AppUtil;
import com.sps.stores.application.ApplicationConstants;
import com.sps.stores.dao.ActivityDao;
import com.sps.stores.model.Activity;

@Service("activityService")
@Transactional
public class ActivitiesServiceImpl implements ActivtiesService {

	@Autowired
	ActivityDao activityDao;
	
	@Autowired
	AppUtil appUtil;
	
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
		List<Activity> listActivity = activityDao.findAllActivities();
		double totInterest = 0.00;
		double totdueAmount  = 0.00;
		for(Activity activity:listActivity){
			if(activity.getActivityCreateDate() != null && activity.getAmount() != null && activity.getActivityType().equalsIgnoreCase(ApplicationConstants.PAYMENT.value()))
			{
				String intrestAmt = appUtil.calculateIntrestAsOfToday(activity.getAmount(), activity.getActivityCreateDate(), activity.getIntrestrate());
				totInterest += Double.valueOf(intrestAmt);
				totdueAmount += Double.valueOf(intrestAmt) + Double.valueOf(activity.getAmount());
				activity.setIntrestAmount(intrestAmt);
				activity.setTotalIntrest(String.valueOf(totInterest));
				activity.setTotalAmount(String.valueOf(totdueAmount));
			}
		}
		return listActivity;
	}

	@Override
	public List<Activity> findAllActivitiesByDate(String date) {
		
		return activityDao.findAllActivitiesByDate(date);
	}

	/**
	 * 
	 */
	@Override
	public List<Activity> findAllActivities(String location, String custId, String date) {
		List<Activity> listActivity = new ArrayList<>();
		double totInterest = 0.00;
		double totdueAmount  = 0.00;
		if(custId!= null )
		{
			listActivity = activityDao.findAllActivities(location, custId, date);
			for(Activity activity:listActivity){
				if(activity.getActivityCreateDate() != null && activity.getAmount() != null && activity.getActivityType().equalsIgnoreCase(ApplicationConstants.PAYMENT.value()))
				{
					String intrestAmt = appUtil.calculateIntrestAsOfToday(activity.getAmount(), activity.getActivityCreateDate(), activity.getIntrestrate());
					totInterest += Double.valueOf(intrestAmt);
					totdueAmount += Double.valueOf(intrestAmt) + Double.valueOf(activity.getAmount());
					activity.setIntrestAmount(intrestAmt);
					activity.setTotalIntrest(String.valueOf(totInterest));
					activity.setTotalAmount(String.valueOf(totdueAmount));
				}
			}
		return listActivity;
		}
		else 
			return listActivity;
	}

}
