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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sps.stores.application.ApplicationConstants;
import com.sps.stores.model.Activity;
import com.sps.stores.model.Customer;
import com.sps.stores.model.Location;
import com.sps.stores.model.Transaction;

@Controller
@RequestMapping("/")
public class ActivityController extends AbstractAppController{

	/**
	 * This method will list all activities created today.
	 */
	@RequestMapping(value = { "/activityList" }, method = {RequestMethod.POST,RequestMethod.GET})
	public String listActivities(ModelMap model,HttpServletRequest request) {
		String custId = request.getParameter("custId");
		String locId = request.getParameter("locId");
		List<Activity> activities = activityService.findAllActivities(locId, custId, "");
		if(activities!= null && !activities.isEmpty()){
		model.addAttribute("total",activities.get(activities.size()-1).getTotalAmount());
		model.addAttribute("totalIntrest",activities.get(activities.size()-1).getTotalIntrest());
		}
		model.addAttribute("activities", activities);
		List<Location> locations = locationService.findAllLocations();
		model.addAttribute("locations",locations);
		List<Customer> customers = customerService.findAllCustomers();
		model.addAttribute("customers",customers);
		List<Customer> customerList = customerService.findAllCustomer(locId);
		model.addAttribute("customerList",customerList);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("custId",custId);
		model.addAttribute("locId",locId);
		return "activityList";
	}
	
	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/newActivity" }, method = RequestMethod.POST)
	public String saveActivity(@Valid Activity activity, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "addactivity";
		}

		activityService.saveActivity(activity);

		model.addAttribute("success", "Activity For customer " + activity.getCustId() +" saved successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		
		return "addactivitysuccess";
	}
	
	@RequestMapping(value = { "/close-activity-{id}" }, method = RequestMethod.GET)
	public String closeActivity(@PathVariable String id,ModelMap model) {
		Activity activity = activityService.findById(Integer.parseInt(id));
		String todayDate = (new SimpleDateFormat("YYYY-MM-dd")).format(new Date());
		activity.setClosingDate(todayDate);
		activity.setStatus(ApplicationConstants.CLOSE.value());
		activityService.updateActivity(activity);

		model.addAttribute("success", "Activity For customer " + activity.getCustId() +" closed successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		
		return "addactivitysuccess";
	}
	
	/**
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/newActivity" }, method = RequestMethod.GET)
	public String newActivity(ModelMap model,HttpServletRequest request) {
		
		Activity activity = new Activity();
		model.addAttribute("activity", activity);
		model.addAttribute("edit", false);
		List<Customer> customers = customerService.findAllCustomers();
		model.addAttribute("customers", customers);
		List<Location> locations = locationService.findAllLocations();
		model.addAttribute("locations",locations);
		
		model.addAttribute("loggedinuser", getPrincipal());
		return "addactivity";
	}
	
	@RequestMapping(value = { "/addActivity-{transId}-{locId}-{custId}" }, method = RequestMethod.GET)
	public ModelAndView addActivity(@PathVariable String transId,@PathVariable String locId,@PathVariable String custId,ModelMap model,RedirectAttributes redir) {
		
		Activity activity = new Activity();
		activity.setTransId(Integer.parseInt(transId));
		model.addAttribute("activity", activity);
		model.addAttribute("custId",custId);
		model.addAttribute("locId",locId);
		model.addAttribute("edit", false);
		List<Customer> customers = customerService.findAllCustomers();
		model.addAttribute("customers", customers);
		List<Location> locations = locationService.findAllLocations();
		model.addAttribute("locations",locations);
		ModelAndView modelAndView = new ModelAndView("redirect:newActivity");
		redir.addFlashAttribute("custId",custId);
		redir.addFlashAttribute("locId",locId);
		redir.addFlashAttribute("transId",transId);
		redir.addFlashAttribute("loggedinuser",getPrincipal());
		model.addAttribute("loggedinuser", getPrincipal());
		return modelAndView;
		
	}
	
	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-activity-{id}" }, method = {RequestMethod.GET,RequestMethod.POST})
	public String editActivity(@PathVariable String id, ModelMap model) {
		Activity activity = activityService.findById(Integer.parseInt(id));
		model.addAttribute("activity", activity);
		model.addAttribute("edit", true);
		List<Customer> customers = customerService.findAllCustomers();
		model.addAttribute("customers", customers);
		model.addAttribute("loggedinuser", getPrincipal());
		return "addactivity";
	}
	
	@RequestMapping(value = { "/list-activity-{transId}" }, method = RequestMethod.GET)
	public String listPayments(@PathVariable String transId, ModelMap model) {
		Transaction transaction = transactionService.findById(Integer.parseInt(transId));
		List<Activity> activities = activityService.getAllActivityForTransaction(transaction.getId(),false);
	
			model.addAttribute("transaction",transaction);
			
			model.addAttribute("custId",transaction.getCustId());
			model.addAttribute("locId",transaction.getCustomer().getLocation());
			model.addAttribute("activities", activities);
			
			List<Customer> customers = customerService.findAllCustomers();
			model.addAttribute("customerList", customers);
			List<Location> locations = locationService.findAllLocations();
			model.addAttribute("locations",locations);
			return "activityList";
		
	}
	
	@RequestMapping(value = { "/delete-activity-{id}" }, method = {RequestMethod.GET,RequestMethod.POST})
	public String deleteTransaction(@PathVariable String id, ModelMap model) {
		
		activityService.deleteActivityByActivityId(id);
		
		model.addAttribute("success", "Transaction For customer " + id +" deleted successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		
		return "addactivitysuccess";
	}

}
