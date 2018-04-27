package com.sps.stores.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sps.stores.application.AppUtil;
import com.sps.stores.application.ApplicationConstants;
import com.sps.stores.dao.partner.PartnerDao;
import com.sps.stores.dao.partner.PartnerTransactionDao;
import com.sps.stores.model.Partner;
import com.sps.stores.model.PartnerTransaction;

@Service("partnerService")
@Transactional
public class PartnerServiceImpl implements PartnerService {

	@Autowired
	PartnerDao partnerDao;
	
	@Autowired
	PartnerTransactionDao partnerTransactionDao;
	
	@Autowired
	PartnerTransService partnerTransService;
	
	@Autowired
	AppUtil appUtil;
	
	@Override
	public Partner findById(int id) {
		
		return partnerDao.findById(id);
	}

	@Override
	public Partner findByName(String name) {
		return partnerDao.findByName(name);
		
	}

	@Override
	public void savePartner(Partner Partner) {
		partnerDao.save(Partner);
		
	}

	@Override
	public void updatePartner(Partner Partner) {
		partnerDao.save(Partner);
		
	}

	@Override
	public void deletePartnerById(String id) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public List<Partner> findAllPartners() {
		
		List<Partner> listPartners = partnerDao.findAllPartners();
		
		for(Partner partner:listPartners){
			double dueAmount = calculateTotalDueAmount(partnerTransService.findAllPartnerTrans(String.valueOf(partner.getId()),null,null));
			partner.setDueAmount(String.valueOf(appUtil.formatDouble(dueAmount)));
		}
		return listPartners;
	}
	
	/**
	 * @param custId
	 * @param listActivity
	 * @return
	 */
	private double calculateTotalDueAmount(List<PartnerTransaction> listTrans) {
		double totdueAmount  = 0.00;
			for(PartnerTransaction trans:listTrans){
				if(trans.getStatus().equalsIgnoreCase(ApplicationConstants.OPEN.value())){
					totdueAmount+=appUtil.formatDouble(Double.parseDouble(trans.getDueAmount()));
					}
				}
			
		return totdueAmount;
		
	}

	@Override
	public List<Partner> findAllPartnersList() {
	
		return partnerDao.findAllPartners();
	}

	
	

	
}
