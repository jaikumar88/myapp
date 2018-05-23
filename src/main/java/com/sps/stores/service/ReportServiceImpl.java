package com.sps.stores.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sps.stores.application.AppUtil;
import com.sps.stores.dao.transaction.TransactionDao;
import com.sps.stores.model.SalesReport;
import com.sps.stores.model.Transaction;
@Service("reportService")
@Transactional
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	TransactionDao transactionDao;


	@Autowired
	AppUtil appUtil;
	
	@Override
	public List<SalesReport> getSaleReports(String startDate, String endDate) {
		List<SalesReport> salesReport = new ArrayList();
		Date stDate = null;
		Date edDate = null;
		if(startDate != null && !"".equalsIgnoreCase(startDate))
		stDate = appUtil.stringToDate(startDate);
		if(endDate != null && !"".equalsIgnoreCase(endDate))
			edDate = appUtil.stringToDate(endDate);
		List<Transaction> transactions = transactionDao.findAllTransactions(null,null,stDate,edDate);
		Date prevoiusDate = null;
		SalesReport saleReport = new SalesReport();
		for(Transaction trans:transactions){
			if(prevoiusDate == null)
				prevoiusDate = trans.getActivityCreateDate();
			saleReport.setReportDate(appUtil.dateToString(trans.getActivityCreateDate()));
			if(trans.getActivityCreateDate().equals(prevoiusDate)){
				saleReport.setSaleAmount(saleReport.getSaleAmount() + Double.parseDouble(trans.getTotalAmount()));
				saleReport.setExpenses(saleReport.getExpenses() + Double.parseDouble(trans.getTotalExpense().equalsIgnoreCase("")?"0.00":trans.getTotalExpense()));
				saleReport.setDueAmount(saleReport.getDueAmount() + Double.parseDouble(trans.getDueAmount().equalsIgnoreCase("")?"0.00":trans.getDueAmount()));
				saleReport.setTotalProducts(saleReport.getTotalProducts() + Double.parseDouble(trans.getQuantity().equalsIgnoreCase("")?"0.00":trans.getQuantity()));
				saleReport.setTotalWeight(saleReport.getTotalWeight() + Double.parseDouble(trans.getWeight().equalsIgnoreCase("")?"0.00":trans.getWeight()));
				saleReport.setProfitLoss(saleReport.getProfitLoss() + Double.parseDouble(trans.getTotalAmount().equalsIgnoreCase("")?"0.00":trans.getTotalAmount())*1.5/100);
			} else{
				prevoiusDate = trans.getActivityCreateDate();
				salesReport.add(saleReport);
				saleReport = new SalesReport();
				saleReport.setReportDate(appUtil.dateToString(trans.getActivityCreateDate()));
				saleReport.setSaleAmount(saleReport.getSaleAmount() + Double.parseDouble(trans.getTotalAmount()));
				saleReport.setExpenses(saleReport.getExpenses() + Double.parseDouble(trans.getTotalExpense().equalsIgnoreCase("")?"0.00":trans.getTotalExpense()));
				saleReport.setDueAmount(saleReport.getDueAmount() + Double.parseDouble(trans.getDueAmount().equalsIgnoreCase("")?"0.00":trans.getDueAmount()));
				saleReport.setTotalProducts(saleReport.getTotalProducts() + Double.parseDouble(trans.getQuantity().equalsIgnoreCase("")?"0.00":trans.getQuantity()));
				saleReport.setTotalWeight(saleReport.getTotalWeight() + Double.parseDouble(trans.getWeight().equalsIgnoreCase("")?"0.00":trans.getWeight()));
				saleReport.setProfitLoss(saleReport.getProfitLoss() + Double.parseDouble(trans.getTotalAmount().equalsIgnoreCase("")?"0.00":trans.getTotalAmount())*1.5/100);
			}
		}
		salesReport.add(saleReport);
		return salesReport;
	}
	
	

}
