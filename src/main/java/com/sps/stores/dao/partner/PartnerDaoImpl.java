package com.sps.stores.dao.partner;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sps.stores.dao.AbstractDao;
import com.sps.stores.model.Partner;

@Repository("partnerDao")
public class PartnerDaoImpl extends AbstractDao<Integer, Partner> implements PartnerDao {

	@Override
	public Partner findById(int id) {
		return getByKey(id);
	}

	
	@Override
	public void save(Partner partner) {
		persist(partner);

	}

	
	

	@Override
	public void delete(Partner partner) {
		super.delete(partner);
	}

	@Override
	public void deleteById(String id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		Partner partner = (Partner)crit.uniqueResult();
		super.delete(partner);
	}


	@Override
	public Partner findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Partner> findAllPartners() {
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("shopNo"));
		List<Partner> partners = (List<Partner>) criteria.list();
		return partners;
		
	}


	

}
