package com.sps.stores.dao;

import java.sql.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sps.stores.model.Activity;

@Repository("activityDao")
public class ActivityDaoImpl extends AbstractDao<Integer, Activity> implements ActivityDao {

	@Override
	public Activity findById(int id) {
		return getByKey(id);
	}

	@Override
	public Activity findByDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Activity activity) {
		persist(activity);

	}

	
	@Override
	public List<Activity> findAllActivities() {
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("activityCreateDate"));
		//criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Activity> activities = (List<Activity>) criteria.list();
		for(Activity activity : activities){
			Hibernate.initialize(activity.getOwner());
		}
		return activities;
	}

	@Override
	public List<Activity> findAllActivitiesByDate(String date) {
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("activityCreateDate"));
		//criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		criteria.add(Restrictions.eq("activityCreateDate", date));
		List<Activity> activities = (List<Activity>) criteria.list();
		for(Activity activity : activities){
			Hibernate.initialize(activity.getOwner());
		}
		return activities;
	}

	@Override
	public void delete(Activity activity) {
		delete(activity);
	}

	@Override
	public void deleteById(String id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("Id", id));
		Activity activity = (Activity)crit.uniqueResult();
		delete(activity);
	}

}
