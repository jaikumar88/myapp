package com.sps.stores.service;

import java.util.List;

import com.sps.stores.model.Partner;

public interface PartnerService {

	Partner findById(int id);
	
	Partner findByName(String name);
	
	void savePartner(Partner partner);
	
	void updatePartner(Partner partner);
	
	void deletePartnerById(String id);

	List<Partner> findAllPartners(); 
	
	List<Partner> findAllPartnersList(); 
	
	
}
