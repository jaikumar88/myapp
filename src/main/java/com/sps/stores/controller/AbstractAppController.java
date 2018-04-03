package com.sps.stores.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;

import com.sps.stores.application.AppUtil;
import com.sps.stores.service.ActivtiesService;
import com.sps.stores.service.CustomerService;
import com.sps.stores.service.LocationService;
import com.sps.stores.service.PartnerService;
import com.sps.stores.service.PartnerTransService;
import com.sps.stores.service.StoreService;
import com.sps.stores.service.TransactionService;
import com.sps.stores.service.UserProfileService;
import com.sps.stores.service.UserService;

public abstract class AbstractAppController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	StoreService storeService;
	
	@Autowired
	ActivtiesService activityService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	LocationService locationService;
	
	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	MessageSource messageSource;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
	
	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;
	
	@Autowired
	AppUtil appUtil;
	
	@Autowired
	PartnerService partnerService;
	
	@Autowired
	PartnerTransService partnerTransService;
	
	/**
	 * This method returns the principal[user-name] of logged-in user.
	 */
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
	

}
