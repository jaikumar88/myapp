package com.sps.stores.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sps.stores.model.Customer;
import com.sps.stores.model.Location;

@Controller
@RequestMapping("/")
public class CustomerController extends AbstractAppController{

	/**
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/newCustomer" }, method = RequestMethod.GET)
	public String newCustomer(ModelMap model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		model.addAttribute("edit", false);
		List<Location> locations = locationService.findAllLocations();
		model.addAttribute("locations",locations);
		model.addAttribute("loggedinuser", getPrincipal());
		return "addcustomer";
	}
	
	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/newCustomer" }, method = RequestMethod.POST)
	public String saveCustomer(@Valid Customer customer, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "addcustomer";
		}

		customerService.saveCustomer(customer);

		model.addAttribute("success", "Customer " + customer.getFirstName() +" saved successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "addcustomersuccess";
	}
	
	/**
	 * This method will list all activities created today.
	 */
	@RequestMapping(value = { "/customerList" }, method = RequestMethod.GET)
	public String listCustomer(ModelMap model) {
		String todayDate = (new SimpleDateFormat("YYYY-MM-dd")).format(new Date());
		List<Customer> customers = customerService.findAllCustomers();
		model.addAttribute("customers", customers);
		model.addAttribute("loggedinuser", getPrincipal());
		return "customerList";
	}
}
