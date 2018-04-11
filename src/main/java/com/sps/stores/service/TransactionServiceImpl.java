package com.sps.stores.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sps.stores.application.AppUtil;
import com.sps.stores.dao.activity.ActivityDao;
import com.sps.stores.dao.transaction.TransactionDao;
import com.sps.stores.model.Activity;
import com.sps.stores.model.Transaction;

@Service("transactionService")
@Transactional
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionDao transactionDao;
	
	@Autowired
	ActivityDao activityDao;
	
	@Autowired
	AppUtil appUtil;
	
	@Override
	public Transaction findById(int id) {
		
		return transactionDao.findById(id);
	}

	@Override
	public Transaction findByDate(String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveTransaction(Transaction transaction) {
		transactionDao.save(transaction);

	}

	@Override
	public void updateTransaction(Transaction transaction) {
		Transaction entity = transactionDao.findById(transaction.getId());
		entity.setStatus(transaction.getStatus());
		entity.setCloseDate(transaction.getCloseDate());


	}

	@Override
	public void deleteTransactionByTransactionId(String id) {
		transactionDao.deleteById(id);
	}

	@Override
	public List<Transaction> findAllTransactions() {
		List<Transaction> listTransaction = transactionDao.findAllTransaction();
		
		return listTransaction;
	}

	@Override
	public List<Transaction> findAllTransactionsByDate(String date) {
		List<Transaction> transactions = transactionDao.findAllTransactionByDate(appUtil.stringToDate(date));
		
		for(Transaction trans: transactions){
			double dueAmount = 0.00;
			List<Activity> activities = activityDao.findAllActivities(trans.getId());
			for(Activity activity: activities){
				dueAmount += Double.parseDouble(activity.getAmount());
			}
			trans.setFinalDue(String.valueOf(Double.parseDouble(trans.getDueAmount()) - dueAmount));
		}
		return transactions;
	}

	/**
	 * 
	 */
	@Override
	public List<Transaction> findAllTransactions(String location, String custId, String date) {
			Date dt = null;
			if(date != null && !"".equalsIgnoreCase(date))
			dt = appUtil.stringToDate(date);
			List<Transaction> transactions = transactionDao.findAllTransactions(location, custId, dt);
			for(Transaction trans: transactions){
				double dueAmount = 0.00;
				List<Activity> activities = activityDao.findAllActivities(trans.getId());
				for(Activity activity: activities){
					dueAmount += Double.parseDouble(activity.getAmount());
				}
				trans.setFinalDue(String.valueOf(Double.parseDouble(trans.getDueAmount()) - dueAmount));
			}
			return transactions;
		
		
	}

	@Override
	public void delete(Transaction trans) {
		transactionDao.delete(trans);
		
	}

	@Override
	public List<Transaction> findAllTransactions(String location, String custId, String startDate, String endDate) {
		Date stDate = null;
		Date edDate = null;
		if(startDate != null && !"".equalsIgnoreCase(startDate))
		stDate = appUtil.stringToDate(startDate);
		if(endDate != null && !"".equalsIgnoreCase(endDate))
			edDate = appUtil.stringToDate(endDate);
		List<Transaction> transactions = transactionDao.findAllTransactions(location, custId, stDate, edDate);
		for(Transaction trans: transactions){
			double dueAmount = 0.00;
			List<Activity> activities = activityDao.findAllActivities(trans.getId());
			for(Activity activity: activities){
				dueAmount += Double.parseDouble(activity.getAmount());
			}
			trans.setFinalDue(String.valueOf(Double.parseDouble(trans.getDueAmount()) - dueAmount));
		}
		return transactions;
		
	}

}
