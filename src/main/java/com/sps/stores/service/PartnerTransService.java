package com.sps.stores.service;

import java.util.List;

import com.sps.stores.model.Partner;
import com.sps.stores.model.PartnerTransaction;
import com.sps.stores.model.Transaction;

public interface PartnerTransService {

	PartnerTransaction findById(int id);
	
	List<PartnerTransaction> findByPartnerId(String id);
	
	void savePartnerTrans(PartnerTransaction partnerTrans);
	
	void savePartnerTrans(Transaction custTrans,String partnerId);
	
	void updatePartnerTrans(PartnerTransaction partnerTrans);
	
	void deletePartnerTransById(String id);

	List<PartnerTransaction> findAllPartnerTrans(); 
	
	List<PartnerTransaction> findAllPartnerTrans(String partnerId,String startDate,String endDate);
	
}
