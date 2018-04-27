package com.sps.stores.controller;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sps.stores.model.Location;
import com.sps.stores.model.Store;
import com.sps.stores.model.Transaction;
import com.sps.stores.model.User;
import com.sps.stores.model.UserProfile;


/**
 * 
 */
@Controller
@RequestMapping("/")
@SessionAttributes({"roles"})
public class AppController extends AbstractAppController {
	
	@RequestMapping(value = { "/"}, method = RequestMethod.GET)
	public String homePage(ModelMap model,HttpServletRequest request) {
		String todayDate = appUtil.dateToString(new Date());
		if(request.getParameter("todayDate")!=null){
			todayDate = request.getParameter("todayDate");
		}
		List<Transaction> transactions = transactionService.findAllTransactionsByDate(todayDate);
		model.addAttribute("transactions", transactions);
		double total=0.00;
		double totalDue= 0.00;
		for(Transaction trans:transactions){
			if(trans!=null){
				total+= "".equalsIgnoreCase(trans.getTotalAmount())?0.00:Double.valueOf(trans.getTotalAmount());
				totalDue+= "".equalsIgnoreCase(trans.getDueAmount())?0.00:Double.valueOf(trans.getDueAmount());
			}
		}
		model.addAttribute("totals", total);
		model.addAttribute("totalsDue", totalDue);
		model.addAttribute("loggedinuser", getPrincipal());
		return "home";
	}

	@RequestMapping(value = { "/products"}, method = RequestMethod.GET)
	public String productsPage(ModelMap model) {
		return "products";
	}

	@RequestMapping(value = { "/contactus"}, method = RequestMethod.GET)
	public String contactUsPage(ModelMap model) {
		return "contactus";
	}
	
	/**
	 * This method will list all existing users.
	 */
	@RequestMapping(value = { "/userList" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {

		List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);
		model.addAttribute("loggedinuser", getPrincipal());
		return "userlist";
	}

	/**
	 * This method will list all existing users.
	 */
	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String home(ModelMap model,HttpServletRequest request) {
		String todayDate = appUtil.dateToString(new Date());
		if(request.getParameter("todayDate")!=null){
			todayDate = request.getParameter("todayDate");
		}
		List<Transaction> transactions = transactionService.findAllTransactionsByDate(todayDate);
		model.addAttribute("transactions", transactions);
		double total=0.00;
		double totalDue= 0.00;
		for(Transaction trans:transactions){
			if(trans!=null){
				total+= "".equalsIgnoreCase(trans.getTotalAmount())?0.00:Double.valueOf(trans.getTotalAmount());
				totalDue+= "".equalsIgnoreCase(trans.getDueAmount())?0.00:Double.valueOf(trans.getDueAmount());
			}
		}
		model.addAttribute("totals", total);
		model.addAttribute("totalsDue", totalDue);
		model.addAttribute("loggedinuser", getPrincipal());
		return "home";
	}
	/**
	 * This method will list all existing users.
	 */
	@RequestMapping(value = { "/storeList" }, method = RequestMethod.GET)
	public String listStores(ModelMap model) {

		List<Store> stores = storeService.findAllStores();
		model.addAttribute("stores", stores);
		model.addAttribute("loggedinuser", getPrincipal());
		return "storelist";
	}
	
	
	
	
	
	
	
	
	/**
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/newstore" }, method = RequestMethod.GET)
	public String newStore(ModelMap model) {
		Store store = new Store();
		model.addAttribute("store", store);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "addstore";
	}
	
	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/newstore" }, method = RequestMethod.POST)
	public String saveStore(@Valid Store store, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "addstore";
		}

		if(storeService.isStoreUnique(store.getId(), store.getStoreId(),store.getCountry())){
			FieldError ssoError =new FieldError("store","storeId",messageSource.getMessage("non.unique.storeId", new String[]{store.getStoreId(),store.getCountry()}, Locale.getDefault()));
		    result.addError(ssoError);
			return "addstore";
		}
		
		
		storeService.saveStore(store);

		model.addAttribute("success", "Store " + store.getStoreName() +" saved successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		
		return "addstoresuccess";
	}
	
	/**
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/newLocation" }, method = RequestMethod.GET)
	public String newLocation(ModelMap model) {
		Location location = new Location();
		model.addAttribute("location", location);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "addlocation";
	}
	
	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/newLocation" }, method = RequestMethod.POST)
	public String saveLocation(@Valid Location location, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "addlocation";
		}

		if(locationService.isLocationUnique(location.getLocation())){
			FieldError ssoError =new FieldError("location","id",messageSource.getMessage("non.unique.locId", new String[]{location.getLocation()}, Locale.getDefault()));
		    result.addError(ssoError);
			return "addlocation";
		}
		
		
		locationService.saveLocation(location);

		model.addAttribute("success", "Location " + location.getLocation() +" saved successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		
		return "addstoresuccess";
	}
	/**
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "registration";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique annotation 
		 * and applying it on field [sso] of Model class [User].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
			FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
		    result.addError(ssoError);
			return "registration";
		}
		
		userService.saveUser(user);

		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " registered successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		
		return "registrationsuccess";
	}

	
	@RequestMapping(value = { "/delete-transaction-{id}" }, method = {RequestMethod.GET,RequestMethod.POST})
	public String deleteTransaction(@PathVariable String id, ModelMap model) {
		Transaction transaction = transactionService.findById(Integer.parseInt(id));

		transactionService.delete(transaction);
		
		model.addAttribute("success", "Transaction For customer " + transaction.getId() +" deleted successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		
		return "addtransactionsuccess";
	}

	
	
	
	
	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String ssoId, ModelMap model) {
		User user = userService.findBySSO(ssoId);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "registration";
	}
	
	/**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result,
			ModelMap model, @PathVariable String ssoId) {

		if (result.hasErrors()) {
			return "registration";
		}
		userService.updateUser(user);

		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " updated successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "registrationsuccess";
	}

	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-store-{storeId}/{country}" }, method = RequestMethod.GET)
	public String editStore(@PathVariable String storeId,@PathVariable String country, ModelMap model) {
		Store store = storeService.findByStoreIdAndCountry(storeId, country);
		model.addAttribute("store", store);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "addstore";
	}
	
	/**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-store-{storeId}/{country}" }, method = RequestMethod.POST)
	public String updateStore(@Valid Store store,@PathVariable String country, BindingResult result,
			ModelMap model, @PathVariable String storeId) {

		if (result.hasErrors()) {
			return "addstore";
		}


		storeService.updateStore(store);

		model.addAttribute("success", "Store " + store.getStoreName() + " updated successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "addstoresuccess";
	}
	
	/**
	 * This method will delete an user by it's SSOID value.
	 */
	@RequestMapping(value = { "/delete-user-{ssoId}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String ssoId) {
		userService.deleteUserBySSO(ssoId);
		return "redirect:/home";
	}
	
	/**
	 * This method will delete a store by it's store Id value.
	 */
	@RequestMapping(value = { "/delete-store-{storeId}/{country}" }, method = RequestMethod.GET)
	public String deleteStore(@PathVariable String storeId,@PathVariable String country) {
		storeService.deleteStoreByStoreIdAndCountry(storeId, country);
		return "redirect:/storeList";
	}

	/**
	 * This method will provide UserProfile list to views
	 */
	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
	}
	
	
	/**
	 * This method handles Access-Denied redirect.
	 */
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "accessDenied";
	}

	/**
	 * This method handles login GET requests.
	 * If users is already logged-in and tries to goto login page again, will be redirected to list page.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		if (isCurrentAuthenticationAnonymous()) {
			return "login";
	    } else {
	    	return "redirect:/home";  
	    }
	}

	/**
	 * This method handles logout requests.
	 * Toggle the handlers if you are RememberMe functionality is useless in your app.
	 */
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/login?logout";
	}

	
	/**
	 * This method returns true if users is already authenticated [logged-in], else false.
	 */
	private boolean isCurrentAuthenticationAnonymous() {
	    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return authenticationTrustResolver.isAnonymous(authentication);
	}
	

	
	/**
	 * This method will list all partners created.
	 */
	@RequestMapping(value = { "/locationList" }, method = RequestMethod.GET)
	public String listLocation(ModelMap model) {
		List<Location> locations = locationService.findAllLocations();
		model.addAttribute("locations", locations);
		model.addAttribute("loggedinuser", getPrincipal());
		return "locationList";
	}
	

}