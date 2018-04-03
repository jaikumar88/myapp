package com.sps.stores.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sps.stores.application.AppUtil;
import com.sps.stores.dao.partner.PartnerTransactionDao;
import com.sps.stores.model.PartnerTransaction;
import com.sps.stores.model.Transaction;

@Service("partnerTransactionService")
@Transactional
public class PartnerTransServiceImpl implements PartnerTransService {

	
	
	@Autowired
	PartnerTransactionDao partnerTransactionDao;
	
	@Autowired
	AppUtil appUtil;
	
	@Override
	public PartnerTransaction findById(int id) {
		
		return partnerTransactionDao.findById(id);
	}

	

	@Override
	public void savePartnerTrans(PartnerTransaction partnerTransaction) {
		partnerTransactionDao.save(partnerTransaction);
		
	}

	@Override
	public void updatePartnerTrans(PartnerTransaction partnerTransaction) {
		//partnerTransactionDao.save(partnerTransaction);
		
	}

	@Override
	public void deletePartnerTransById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PartnerTransaction> findAllPartnerTrans() {
		return partnerTransactionDao.findAllPartnerTransactions();
	}
	
	@Override
	public List<PartnerTransaction> findAllPartnerTrans(String partnerId,String startDate,String endDate) {
		
		return partnerTransactionDao.findAllPartnerTransactionsByPartnerId(partnerId,startDate,endDate);
	}

	@Override
	public List<PartnerTransaction> findByPartnerId(String id) {
		
		return partnerTransactionDao.findAllPartnerTransactionsByPartnerId(id);
	}



	@Override
	public void savePartnerTrans(Transaction custTrans,String partnerId) {
		PartnerTransaction trans = convertFromTrans(custTrans);
		trans.setPartnerId(Integer.parseInt(partnerId));
		partnerTransactionDao.save(trans);
		
	}



	private PartnerTransaction convertFromTrans(Transaction custTrans) {
		PartnerTransaction trans = new PartnerTransaction();
		if(custTrans != null){
			trans.setActivityCreateDate(custTrans.getActivityCreateDate());
			trans.setDeductionPercent(custTrans.getDeductionPercent());
			//trans.setDueAmount(custTrans.getDueAmount());
			//trans.setExpPerItem(custTrans.getExpPerItem());
			//trans.setMemo(custTrans.getMemo());
			//trans.setOtherExpense(custTrans.getOtherExpense());
			trans.setProductType(custTrans.getProductType());
			trans.setQuantity(custTrans.getQuantity());
			trans.setStatus(custTrans.getStatus());
			trans.setTotalAmount(custTrans.getTotalAmount());
			//trans.setTotalExpense(custTrans.getTotalExpense());
			trans.setWeight(custTrans.getWeight());
		}
		return trans;
	}

	
	
	

	
}
