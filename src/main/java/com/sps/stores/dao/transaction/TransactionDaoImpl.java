package com.sps.stores.dao;

import java.sql.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.sps.stores.model.Transaction;

@Repository("transactionDao")
public class TransactionDaoImpl extends AbstractDao<Integer, Transaction> implements TransactionDao {

	@Override
	public Transaction findById(int id) {
		Transaction transaction = getByKey(id);
		if(transaction!= null){
			Hibernate.initialize(transaction.getCustomer());
		}
		return transaction;
	}

	@Override
	public Transaction findByDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Transaction trans) {
		persist(trans);

	}

	
	@Override
	public List<Transaction> findAllTransaction() {
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("activityCreateDate"));
		//criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Transaction> transactions = (List<Transaction>) criteria.list();
		for(Transaction transaction : transactions){
			Hibernate.initialize(transaction.getCustomer());
		}
		return transactions;
	}

	@Override
	public List<Transaction> findAllTransactionByDate(Date date) {
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("activityCreateDate"));
		//criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		criteria.add(Restrictions.eq("activityCreateDate", date));
		List<Transaction> transactions = (List<Transaction>) criteria.list();
		for(Transaction transaction : transactions){
			Hibernate.initialize(transaction.getCustomer());
		}
		return transactions;
	}

	@Override
	public void delete(Transaction trans) {
		super.delete(trans);
	}

	@Override
	public void deleteById(String id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", id));
		Transaction trans = (Transaction)crit.uniqueResult();
		super.delete(trans);
	}

	@Override
	public List<Transaction> findAllTransactions(String location, String custId, String date) {
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("activityCreateDate"));
		//criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		//criteria.add(Restrictions.eq("location", location));
		if(custId != null && !"".equalsIgnoreCase(custId))
		criteria.add(Restrictions.eq("custId", Integer.parseInt(custId)));
		//criteria.add(Restrictions.gt("activityCreateDate", date));
		List<Transaction> transactions = (List<Transaction>) criteria.list();
		for(Transaction transaction : transactions){
			Hibernate.initialize(transaction.getCustomer());
		}
		return transactions;

	}

}
