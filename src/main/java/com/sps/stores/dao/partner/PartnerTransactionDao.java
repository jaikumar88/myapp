package com.sps.stores.dao.partner;

import java.util.List;

import com.sps.stores.model.PartnerTransaction;

public interface PartnerTransactionDao {

	PartnerTransaction findById(int id);
	
	PartnerTransaction findByName(String name);
	
	void save(PartnerTransaction partnerTrans);
	
	void deleteById(String id);
	
	List<PartnerTransaction> findAllPartnerTransactions();
	
	List<PartnerTransaction> findAllPartnerTransactionsByPartnerId(String id);
	
	List<PartnerTransaction> findAllPartnerTransactionsByPartnerId(String partnerId,String startDate,String endDate);
	
	void delete(PartnerTransaction  partner);
}
