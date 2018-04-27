package com.sps.stores.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sps.stores.application.ApplicationConstants;
import com.sps.stores.model.Customer;
import com.sps.stores.model.Greeting;
import com.sps.stores.model.Location;
import com.sps.stores.model.Partner;
import com.sps.stores.model.Product;
import com.sps.stores.model.Transaction;

@RestController
public class MobileController extends AbstractAppController {

	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;
	
   
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	 String response = null;
    	if(isCurrentAuthenticationAnonymous()){
    		response = "Welcome, %s!";
    	} else {
    		response = "Sorry, %s!";
    	}
        return new Greeting(counter.incrementAndGet(),
                            String.format(response, name));
    }
    
    /**
	 * This method returns true if users is already authenticated [logged-in], else false.
	 */
	private boolean isCurrentAuthenticationAnonymous() {
	    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return authenticationTrustResolver.isAnonymous(authentication);
	}
	
	@RequestMapping(value = { "/mTransList"}, method = RequestMethod.GET)
	public List<Transaction> homePage(ModelMap model,HttpServletRequest request) {
		String todayDate = appUtil.dateToString(new Date());
		List<Transaction> transactions = transactionService.findAllTransactionsByDate(todayDate);
		return transactions;
	}
	
	/**
	 * This method will list all locations created.
	 */
	@RequestMapping(value = { "/mLocations" }, method = RequestMethod.GET)
	public List<Location> listLocation(ModelMap model) {
		List<Location> locations = locationService.findAllLocations();
		model.addAttribute("locations", locations);
		model.addAttribute("loggedinuser", getPrincipal());
		return locations;
	}
	
	/**
	 * This method will list all partners created.
	 */
	@RequestMapping(value = { "/mPartnerList" }, method = RequestMethod.GET)
	public List<Partner> listPartner(ModelMap model) {
		List<Partner> partners = partnerService.findAllPartners();
		model.addAttribute("partners", partners);
		model.addAttribute("loggedinuser", getPrincipal());
		return partners;
	}
	
	@RequestMapping(value = { "/mCustomerList" }, method = RequestMethod.GET)
	public List<Customer> listCustomer(ModelMap model) {
		
		List<Customer> customers = customerService.findAllCustomers();
		model.addAttribute("customers", customers);
		model.addAttribute("loggedinuser", getPrincipal());
		return customers;
	}
	
	@RequestMapping(value = { "/mNewTransaction" }, method = RequestMethod.POST)
	public Transaction saveTransaction(@RequestBody Transaction transaction) {
		
		transaction.setStatus(ApplicationConstants.OPEN.value());
		Partner partner = partnerService.findByName(transaction.getPartnerId());
		transactionService.saveTransaction(transaction);
		partnerTransService.savePartnerTrans(transaction,String.valueOf(partner.getId()));
		return transaction;
	}
	
	@RequestMapping(value = { "/mProductList" }, method = RequestMethod.GET)
	public List<Product> listCustomer() {
		return productService.findAllProducts();
	}
	
	
}