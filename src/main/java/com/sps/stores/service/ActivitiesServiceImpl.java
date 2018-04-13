package com.sps.stores.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sps.stores.application.AppUtil;
import com.sps.stores.application.ApplicationConstants;
import com.sps.stores.dao.activity.ActivityDao;
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
		Activity entity = activityDao.findById(activity.getId());
		entity.setStatus(activity.getStatus());
		entity.setClosingDate(activity.getClosingDate());

	}

	@Override
	public void deleteActivityByActivityId(String id) {
		activityDao.deleteById(id);
	}

	@Override
	public List<Activity> findAllActivities() {
		List<Activity> listActivity = activityDao.findAllActivities();
		
		return calculateTotalAndDueAmount( listActivity);
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
		List<Activity> listActivity = activityDao.findAllActivities(location, custId, date);
		return calculateTotalAndDueAmount( listActivity);
	}

	
	@Override
	public List<Activity> calculateAnyDueOnCustomer(int custId) {
		List<Activity> activities = activityDao.findAllActivities("", String.valueOf(custId), "");
		List<Activity> openItems = new ArrayList<>();
		for(Activity activity:activities){
			if(activity.getStatus().equalsIgnoreCase(ApplicationConstants.OPEN.value())){
				openItems.add(activity);
			}
		}
		calculateTotalAndDueAmount(openItems);
		return openItems;
	}
	/**
	 * @param custId
	 * @param listActivity
	 * @return
	 */
	private List<Activity> calculateTotalAndDueAmount(List<Activity> listActivity) {
		double totInterest = 0.00;
		double totdueAmount  = 0.00;
		
			for(Activity activity:listActivity){
				if(activity.getStatus().equalsIgnoreCase(ApplicationConstants.OPEN.value())){
				if(activity.getActivityCreateDate() != null && activity.getAmount() != null && 
						activity.getActivityType().equalsIgnoreCase(ApplicationConstants.ADVANCE.value()))
				{
					String intrestAmt = appUtil.calculateIntrestAsOfToday(activity.getAmount(), activity.getActivityCreateDate(), activity.getIntrestrate());
					totInterest += Double.valueOf(intrestAmt);
					activity.setIntrest(intrestAmt);
					totdueAmount += Double.valueOf(intrestAmt) + Double.valueOf(activity.getAmount().equalsIgnoreCase("")?"0.00":activity.getAmount());
					activity.setIntrestAmount(intrestAmt);
					activity.setTotalIntrest(String.valueOf(appUtil.formatDouble(totInterest)));
					activity.setTotalAmount(String.valueOf(appUtil.formatDouble(totdueAmount)));
					
				} else if(activity.getActivityType().equalsIgnoreCase(ApplicationConstants.RECEIVED.value())){
					String intrestAmt = appUtil.calculateIntrestAsOfToday(activity.getAmount(), activity.getActivityCreateDate(), activity.getIntrestrate());
					activity.setIntrest(intrestAmt);
					totInterest -= Double.valueOf(intrestAmt);
					totdueAmount -= Double.valueOf(intrestAmt) + Double.valueOf(activity.getAmount().equalsIgnoreCase("")?"0.00":activity.getAmount());
					activity.setIntrestAmount(intrestAmt);
					activity.setTotalIntrest(String.valueOf(appUtil.formatDouble(totInterest)));
					activity.setTotalAmount(String.valueOf(appUtil.formatDouble(totdueAmount)));
				}
				}
			}
		return listActivity;
		
	}

	@Override
	public List<Activity> getAllActivityForTransaction(int transId,boolean isPartnerTrans) {
		
		return activityDao.findAllActivities(transId,isPartnerTrans);
	}

}
