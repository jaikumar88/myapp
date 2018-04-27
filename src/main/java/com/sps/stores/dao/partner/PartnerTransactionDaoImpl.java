package com.sps.stores.dao.partner;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sps.stores.application.AppUtil;
import com.sps.stores.dao.AbstractDao;
import com.sps.stores.model.PartnerTransaction;

@Repository("partnerTransactionDao")
public class PartnerTransactionDaoImpl extends AbstractDao<Integer, PartnerTransaction> implements PartnerTransactionDao {

	@Autowired
	AppUtil appUtil;
	
	@Override
	public PartnerTransaction findById(int id) {
		return getByKey(id);
	}

	
	@Override
	public void save(PartnerTransaction partnerTrans) {
		persist(partnerTrans);

	}

	
	

	@Override
	public void delete(PartnerTransaction partnerTransaction) {
		super.delete(partnerTransaction);
	}

	@Override
	public void deleteById(String id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		PartnerTransaction partnerTransaction = (PartnerTransaction)crit.uniqueResult();
		super.delete(partnerTransaction);
	}


	@Override
	public PartnerTransaction findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<PartnerTransaction> findAllPartnerTransactions() {
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("activityCreateDate"));
		List<PartnerTransaction> partnersTrans = criteria.list();
		for(PartnerTransaction transaction : partnersTrans){
			Hibernate.initialize(transaction.getPartner());
		}
		return partnersTrans;
		
	}


	@Override
	public List<PartnerTransaction> findAllPartnerTransactionsByPartnerId(String id) {
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("activityCreateDate"));
		criteria.createAlias("partner", "partner");
		criteria.add(Restrictions.eq("partner.id", Integer.parseInt(id)));
		List<PartnerTransaction> partnersTrans = criteria.list();
		
		return partnersTrans;
	}

	@Override
	public List<PartnerTransaction> findAllPartnerTransactionsByPartnerId(String partnerId,String startDate,String endDate) {
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("activityCreateDate"));
		if(partnerId!=null && !"".equalsIgnoreCase(partnerId)){
		criteria.createAlias("partner", "partner");
		criteria.add(Restrictions.eq("partner.id", Integer.parseInt(partnerId)));
		}
		if(startDate != null && !"".equalsIgnoreCase(startDate)){
			criteria.add(Restrictions.ge("activityCreateDate", appUtil.stringToDate(startDate)));
		}
		if(endDate != null && !"".equalsIgnoreCase(endDate)){
			criteria.add(Restrictions.le("activityCreateDate",appUtil.stringToDate(endDate)));
		}
		List<PartnerTransaction> partnersTrans = criteria.list();
		for(PartnerTransaction transaction : partnersTrans){
			Hibernate.initialize(transaction.getPartner());
		}
		return partnersTrans;
	}

	

}
