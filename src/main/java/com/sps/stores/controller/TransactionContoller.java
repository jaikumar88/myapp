package com.sps.stores.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sps.stores.application.ApplicationConstants;
import com.sps.stores.model.Activity;
import com.sps.stores.model.Customer;
import com.sps.stores.model.Location;
import com.sps.stores.model.Partner;
import com.sps.stores.model.Product;
import com.sps.stores.model.Transaction;

@Controller
@RequestMapping("/")
public class TransactionContoller extends AbstractAppController {
	

	@RequestMapping(value = { "/transactionList" }, method = {RequestMethod.POST,RequestMethod.GET})
	public String listTransactions(ModelMap model,HttpServletRequest request) {
		String custId = request.getParameter("custId");
		String locId = request.getParameter("locId");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		
		List<Transaction> transactions = transactionService.findAllTransactions(locId, custId, startDate, endDate);
		model.addAttribute("transactions", transactions);
		
		List<Location> locations = locationService.findAllLocations();
		model.addAttribute("locations",locations);
		List<Customer> customers = customerService.findAllCustomers();
		model.addAttribute("customers",customers);
		List<Customer> customerList = customerService.findAllCustomer(locId);
		model.addAttribute("customerList",customerList);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("custId",custId);
		model.addAttribute("locId",locId);
		model.addAttribute("startDate",startDate);
		model.addAttribute("endDate",endDate);
		return "transactionList";
	}
	
	
	@RequestMapping(value = { "/close-now-{id}" }, method = RequestMethod.GET)
	public String closeTransaction(@PathVariable String id,ModelMap model) {
		Transaction transaction = transactionService.findById(Integer.parseInt(id));
		String todayDate = (new SimpleDateFormat("YYYY-MM-dd")).format(new Date());
		transaction.setCloseDate(todayDate);
		transaction.setStatus(ApplicationConstants.CLOSE.value());
		transactionService.updateTransaction(transaction);;

		model.addAttribute("success", "Transaction For customer " + transaction.getCustId() +" closed successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		//return "success";
		return "addactivitysuccess";
	}
	

	@RequestMapping(value = { "/newTransaction" }, method = RequestMethod.GET)
	public String newTransaction(ModelMap model) {
		Transaction transaction = new Transaction();
		model.addAttribute("transaction", transaction);
		model.addAttribute("edit", false);
		List<Customer> customers = customerService.findAllCustomers();
		model.addAttribute("customers", customers);
		List<Location> locations = locationService.findAllLocations();
		model.addAttribute("locations",locations);
		List<Partner> partners = partnerService.findAllPartnersList();
		model.addAttribute("partners",partners);
		List<Product> products = productService.findAllProducts();
		model.addAttribute("productList",products);
		model.addAttribute("loggedinuser", getPrincipal());
		return "addtransaction";
	}
	
	
	@RequestMapping(value = { "/newTransaction" }, method = RequestMethod.POST)
	public String saveTransaction(@Valid Transaction transaction, BindingResult result,
			ModelMap model,HttpServletRequest request) {
		String partnerId = request.getParameter("partnerId");
		
		if (result.hasErrors()) {
			return "addtransaction";
		}
		transaction.setStatus(ApplicationConstants.OPEN.value());
		transactionService.saveTransaction(transaction);
		partnerTransService.savePartnerTrans(transaction,partnerId);
		model.addAttribute("success", "Transaction For customer " + transaction.getCustId() +" saved successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		
		return "addtransactionsuccess";
	}
	

	@RequestMapping(value = { "/close-check-{id}" }, method = RequestMethod.GET)
	public String closeCheck(@PathVariable String id, ModelMap model) {
		Transaction transaction = transactionService.findById(Integer.parseInt(id));
		List<Activity> activities = activityService.getAllActivityForTransaction(transaction.getId(),false);
		double amount = 0.00;
		for(Activity act: activities){
			if(act.getActivityType() != null && "Payment".equalsIgnoreCase(act.getActivityType())){
				
				amount += Double.parseDouble(act.getAmount());
			}
		}
		double transDue = Double.parseDouble(transaction.getDueAmount());
		if(activities.isEmpty() || amount < transDue){
			model.addAttribute("transaction",transaction);
			model.addAttribute("transId",transaction.getId());
			model.addAttribute("custId",transaction.getCustId());
			model.addAttribute("locId",transaction.getCustomer().getLocation());
			model.addAttribute("activities", activities);
			
			List<Customer> customers = customerService.findAllCustomers();
			model.addAttribute("customerList", customers);
			List<Location> locations = locationService.findAllLocations();
			model.addAttribute("locations",locations);
			return "activityList";
		}else
		{
		model.addAttribute("transaction",transaction);
		model.addAttribute("loggedinuser", getPrincipal());
		return "closeConfirmation";
		}
	}

}
