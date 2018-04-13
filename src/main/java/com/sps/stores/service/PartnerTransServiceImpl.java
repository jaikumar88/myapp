package com.sps.stores.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sps.stores.application.AppUtil;
import com.sps.stores.application.ApplicationConstants;
import com.sps.stores.dao.activity.ActivityDao;
import com.sps.stores.dao.partner.PartnerTransactionDao;
import com.sps.stores.dao.product.ProductDao;
import com.sps.stores.model.Activity;
import com.sps.stores.model.PartnerTransaction;
import com.sps.stores.model.Product;
import com.sps.stores.model.Transaction;

@Service("partnerTransactionService")
@Transactional
public class PartnerTransServiceImpl implements PartnerTransService {

	
	
	@Autowired
	PartnerTransactionDao partnerTransactionDao;
	
	@Autowired
	ActivityDao activityDao;
	
	@Autowired
	AppUtil appUtil;
	
	@Autowired
	ProductDao productDao;
	
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
		PartnerTransaction entity = partnerTransactionDao.findById(partnerTransaction.getId());
		entity.setStatus(partnerTransaction.getStatus());
		entity.setCloseDate(partnerTransaction.getCloseDate());
		
	}

	@Override
	public void deletePartnerTransById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PartnerTransaction> findAllPartnerTrans() {
    List<PartnerTransaction> transactions = partnerTransactionDao.findAllPartnerTransactions();
		
		for(PartnerTransaction trans: transactions){
			double dueAmount = 0.00;
			List<Activity> activities = activityDao.findAllActivities(trans.getId(),true);
			calculateTotalAndDueAmount(activities, trans);

		}
		return transactions;
		
	}
	
	
	@Override
	public List<PartnerTransaction> findAllPartnerTrans(String partnerId,String startDate,String endDate) {
		
		List<PartnerTransaction> transactions = partnerTransactionDao.findAllPartnerTransactionsByPartnerId(partnerId,startDate,endDate);
		
		for(PartnerTransaction trans: transactions){
			double dueAmount = 0.00;
			List<Activity> activities = activityDao.findAllActivities(trans.getId(),true);
			calculateTotalAndDueAmount(activities, trans);

		}
		return transactions;
	}

	@Override
	public List<PartnerTransaction> findByPartnerId(String id) {
		
		List<PartnerTransaction> transactions = partnerTransactionDao.findAllPartnerTransactionsByPartnerId(id);
		for(PartnerTransaction trans: transactions){
			double dueAmount = 0.00;
			List<Activity> activities = activityDao.findAllActivities(trans.getId(),true);
			calculateTotalAndDueAmount(activities, trans);

		}
		return transactions;
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
			trans.setCustId(custTrans.getCustId());
		}
		return trans;
	}

	
	private List<Activity> calculateTotalAndDueAmount(List<Activity> listActivity,PartnerTransaction trans) {
		if(!appUtil.dateToString(new Date()).equalsIgnoreCase(trans.getUpdateDate())){
		if(trans.getStatus().equalsIgnoreCase(ApplicationConstants.OPEN.value())){
		Product product = productDao.findByProductType(trans.getProductType());
		int noOfDaysFree = 0;
		if(product != null){
			noOfDaysFree = Integer.parseInt(product.getNoOfDaysFree());
		}
		
		double totInterest = 0.00;
		double totdueAmount  = 0.00;
		String amount = trans.getTotalAmount();
		String startDate = appUtil.dateToString(trans.getActivityCreateDate());
		String endDate = null;
		totdueAmount = Double.parseDouble(amount);
		if(listActivity.isEmpty()){
			String intrestAmt = appUtil.calculateIntrestBetween(amount, startDate, product.getIntrestRate(),appUtil.dateToString(new Date()));
			totInterest += Double.valueOf(intrestAmt);
		} else {
			for(Activity activity:listActivity){
				endDate = activity.getActivityCreateDate();
				if(activity.getStatus().equalsIgnoreCase(ApplicationConstants.OPEN.value())){
					String intrestAmt = appUtil.calculateIntrestBetween(amount, startDate, product.getIntrestRate(),endDate);
					totInterest += Double.valueOf(intrestAmt);
					startDate = activity.getActivityCreateDate();
					amount = String.valueOf((Double.parseDouble(amount) - Double.parseDouble(activity.getAmount())));
				}
			}
		}
		
		totdueAmount += totInterest;
		trans.setDueAmount(String.valueOf(totdueAmount));
		trans.setFinalDue(trans.getDueAmount());
		trans.setUpdateDate(appUtil.dateToString(new Date()));
	}
		}
	return listActivity;
	}
	
	
}
