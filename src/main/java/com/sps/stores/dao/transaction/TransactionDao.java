package com.sps.stores.dao.transaction;

import java.sql.Date;
import java.util.List;

import com.sps.stores.model.Transaction;

public interface TransactionDao {

	Transaction findById(int id);
	
	Transaction findByDate(Date date);
	
	void save(Transaction transaction);
	
	void deleteById(String id);
	
	List<Transaction> findAllTransaction();
	
	List<Transaction>  findAllTransactionByDate(Date date);
	
	void delete(Transaction transaction);
	
	List<Transaction> findAllTransactions(String location,String custId, Date date);
}
