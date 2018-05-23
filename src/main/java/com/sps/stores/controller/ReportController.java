package com.sps.stores.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sps.stores.application.AppUtil;
import com.sps.stores.model.SalesReport;
import com.sps.stores.model.Transaction;
import com.sps.stores.service.ReportService;
import com.sps.stores.service.TransactionService;

@Controller
@RequestMapping("/")
public class ReportController {

@Autowired
TransactionService transactionService;

@Autowired
ReportService reportService;

@Autowired
AppUtil appUtil;
/**
 * Handle request to download an Excel document
 */
@RequestMapping(value = "/download/**", method = RequestMethod.GET)
public Model download(Model model,HttpServletRequest request) {
	
	String todayDate = appUtil.dateToString(new Date());
	String custId = request.getParameter("custID");
	String locId = request.getParameter("locID");
	String transId = request.getParameter("transId");
	String printToday = request.getParameter("printAll");
	List<Transaction> transactions = new ArrayList<>();
	if(transId!=null && !"".equalsIgnoreCase(transId)){
		transactions.add(transactionService.findById(Integer.parseInt(transId)));
	}else if(printToday != null && !"".equalsIgnoreCase(printToday) && "today".equalsIgnoreCase(printToday)){
		transactions = transactionService.findAllTransactionsByDate(todayDate);
	}
	else{
		transactions = transactionService.findAllTransactions(locId, custId, "");
	}
    model.addAttribute("transactions", transactions);
    model.addAttribute("custId",custId);
    model.addAttribute("FileName",getPrincipal());
    return model;
}



protected String getPrincipal(){
	String userName = null;
	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	if (principal instanceof UserDetails) {
		userName = ((UserDetails)principal).getUsername();
	} else {
		userName = principal.toString();
	}
	return userName;
}

@RequestMapping(value = { "/salesReport" }, method = {RequestMethod.POST,RequestMethod.GET})
public String salesReport(ModelMap model,HttpServletRequest request) {
	
	String startDate = request.getParameter("startDate");
	String endDate = request.getParameter("endDate");
	if(startDate == null || startDate.equalsIgnoreCase("")){
		startDate = appUtil.dateToString(new Date());
	}
	if(endDate == null || endDate.equalsIgnoreCase("")){
		endDate = appUtil.dateToString(new Date());
	}
	List<SalesReport> salesReport = reportService.getSaleReports(startDate, endDate);
	model.addAttribute("salesreport", salesReport);
	model.addAttribute("startDate",startDate);
	model.addAttribute("endDate",endDate);
	return "salesreport";
}



}