package com.sps.stores.service;

import java.util.List;

import com.sps.stores.model.Transaction;

public interface TransactionService {

	Transaction findById(int id);
	
	Transaction findByDate(String date);
	
	void saveTransaction(Transaction transaction);
	
	void updateTransaction(Transaction transaction);
	
	void deleteTransactionByTransactionId(String id);

	List<Transaction> findAllTransactions(); 
	
	List<Transaction> findAllTransactionsByDate(String date); 
	
	List<Transaction> findAllTransactions(String location,String custId, String date);
	
	void delete(Transaction trans);
}
