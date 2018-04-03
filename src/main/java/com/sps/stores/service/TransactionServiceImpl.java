package com.sps.stores.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sps.stores.application.AppUtil;
import com.sps.stores.dao.transaction.TransactionDao;
import com.sps.stores.model.Transaction;

@Service("transactionService")
@Transactional
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionDao transactionDao;
	
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
		
		return transactionDao.findAllTransactionByDate(appUtil.stringToDate(date));
	}

	/**
	 * 
	 */
	@Override
	public List<Transaction> findAllTransactions(String location, String custId, String date) {
			Date dt = null;
			if(date != null && !"".equalsIgnoreCase(date))
			dt = appUtil.stringToDate(date);
			
		
			return transactionDao.findAllTransactions(location, custId, dt);
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
		return transactionDao.findAllTransactions(location, custId, stDate, edDate);
	}

}
