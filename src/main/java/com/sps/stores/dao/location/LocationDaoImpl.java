package com.sps.stores.dao.location;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.sps.stores.dao.AbstractDao;
import com.sps.stores.model.Location;

@Repository("locationDao")
public class LocationDaoImpl extends AbstractDao<Integer, Location> implements LocationDao {

	@Override
	public Location findById(int id) {
		return getByKey(id);
	}

	@Override
	public void save(Location location) {
		persist(location);
		
	}

	@Override
	public void deleteById(String id) {
		deleteById(id);
		
	}

	@Override
	public List<Location> findAllLocations() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("location"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Location> locations = (List<Location>) criteria.list();
		return locations;
		
	}

	

}
