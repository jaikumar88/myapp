package com.sps.stores.dao.partner;

import java.util.List;

import com.sps.stores.model.Partner;

public interface PartnerDao {

	Partner findById(int id);
	
	Partner findByName(String name);
	
	void save(Partner partner);
	
	void deleteById(String id);
	
	List<Partner> findAllPartners();
	
	void delete(Partner partner);
}
