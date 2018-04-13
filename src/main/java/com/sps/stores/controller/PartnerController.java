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
import com.sps.stores.model.Partner;
import com.sps.stores.model.PartnerTransaction;
import com.sps.stores.model.Product;

@Controller
@RequestMapping("/")
public class PartnerController extends AbstractAppController {

	
	
	
	@RequestMapping(value = { "/list-partner-activity-{transId}" }, method = RequestMethod.GET)
	public String listPayments(@PathVariable String transId, ModelMap model) {
		PartnerTransaction transaction = partnerTransService.findById(Integer.parseInt(transId));
		List<Activity> activities = activityService.getAllActivityForTransaction(transaction.getId(),true);
	
			model.addAttribute("transaction",transaction);
			model.addAttribute("transId",transaction.getId());
			model.addAttribute("partnerId",transaction.getPartnerId());
			model.addAttribute("activities", activities);
			
			List<Customer> customers = customerService.findAllCustomers();
			model.addAttribute("customerList", customers);
			List<Location> locations = locationService.findAllLocations();
			model.addAttribute("locations",locations);
			return "partnerActivityList";
		
		
	}
	@RequestMapping(value = { "/close-partnerTrans-{id}" }, method = RequestMethod.GET)
	public String closeTransaction(@PathVariable String id,ModelMap model) {
		PartnerTransaction transaction = partnerTransService.findById(Integer.parseInt(id));
		String todayDate = (new SimpleDateFormat("YYYY-MM-dd")).format(new Date());
		transaction.setCloseDate(todayDate);
		transaction.setStatus(ApplicationConstants.CLOSE.value());
		partnerTransService.updatePartnerTrans(transaction);

		model.addAttribute("success", "Transaction " + transaction.getId() +" closed successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		//return "success";
		return "addactivitysuccess";
	}
	
	
	@RequestMapping(value = { "/addPartnerActivity-{transId}" }, method = RequestMethod.GET)
	public ModelAndView addActivity(@PathVariable String transId,ModelMap model,RedirectAttributes redir) {
		PartnerTransaction transaction = partnerTransService.findById(Integer.parseInt(transId));
		Activity activity = new Activity();
		activity.setPartnerTransId(Integer.parseInt(transId));
		model.addAttribute("activity", activity);
		
		model.addAttribute("edit", false);
		List<Customer> customers = customerService.findAllCustomers();
		model.addAttribute("customers", customers);
		List<Location> locations = locationService.findAllLocations();
		model.addAttribute("locations",locations);
		ModelAndView modelAndView = new ModelAndView("redirect:newActivity");
		redir.addFlashAttribute("partnerTransId",transId);
		redir.addFlashAttribute("loggedinuser",getPrincipal());
		redir.addFlashAttribute("custId",transaction.getCustId());
		redir.addFlashAttribute("locId",customerService.findById(transaction.getCustId()).getLocation());
		model.addAttribute("loggedinuser", getPrincipal());
		return modelAndView;
		
	}
	
	@RequestMapping(value = { "/close-partner-check-{id}" }, method = RequestMethod.GET)
	public String closeCheck(@PathVariable String id, ModelMap model) {
		PartnerTransaction transaction = partnerTransService.findById(Integer.parseInt(id));
		List<Activity> activities = activityService.getAllActivityForTransaction(transaction.getId(),true);
		double amount = 0.00;
		for(Activity act: activities){
			if(act.getActivityType() != null && "Payment".equalsIgnoreCase(act.getActivityType())){
				System.out.println("");
				amount += Double.parseDouble(act.getAmount());
			}
		}
		double transDue = Double.parseDouble(transaction.getTotalAmount()!=null?transaction.getTotalAmount():"0.00");
		if(activities.isEmpty() || amount < transDue){
			model.addAttribute("transaction",transaction);
			model.addAttribute("transId",transaction.getId());
			model.addAttribute("partnerId",transaction.getPartnerId());
			model.addAttribute("activities", activities);
			
			List<Customer> customers = customerService.findAllCustomers();
			model.addAttribute("customerList", customers);
			List<Location> locations = locationService.findAllLocations();
			model.addAttribute("locations",locations);
			return "partnerActivityList";
		}else
		{
		model.addAttribute("transaction",transaction);
		model.addAttribute("loggedinuser", getPrincipal());
		return "closePartnerConfirmation";
		}
	}
	
	/**
	 * This method will provide the medium to add a new partner.
	 */
	@RequestMapping(value = { "/newpartner" }, method = RequestMethod.GET)
	public String newPartner(ModelMap model) {
		Partner partner = new Partner();
		model.addAttribute("partner", partner);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "addpartner";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/newpartner" }, method = RequestMethod.POST)
	public String savePartner(@Valid Partner partner, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "addpartner";
		}
		
		partnerService.savePartner(partner);

		model.addAttribute("success", "Partner " + partner.getFirstName() + " "+ partner.getLastName() + " created successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		//return "success";
		return "addpartnersuccess";
	}
	
	/**
	 * This method will list all partners created.
	 */
	@RequestMapping(value = { "/partnerList" }, method = RequestMethod.GET)
	public String listPartner(ModelMap model) {
		List<Partner> partners = partnerService.findAllPartners();
		model.addAttribute("partners", partners);
		model.addAttribute("loggedinuser", getPrincipal());
		return "partnerList";
	}
	
	/**
	 * This method will list all partners created.
	 */
	@RequestMapping(value = { "/partnerTransList" }, method = {RequestMethod.GET,RequestMethod.POST})
	public String listPartnerTrans(ModelMap model,HttpServletRequest request) {
		String partnerId = request.getParameter(ApplicationConstants.PARTNER_ID.value());
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		partnerId = "".equalsIgnoreCase(partnerId)?null:partnerId;
		List<PartnerTransaction> partnerTrans = partnerTransService.findAllPartnerTrans(partnerId, startDate, endDate);
		model.addAttribute("partnertrans", partnerTrans);
		List<Partner> partners = partnerService.findAllPartnersList();
		model.addAttribute("partnerList",partners);
		model.addAttribute("loggedinuser", getPrincipal());
		return "partnerTransList";
	}
	
	/**
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/newPartnerTrans" }, method = RequestMethod.GET)
	public String newPartnerTrans(ModelMap model) {
		PartnerTransaction partnerTrans = new PartnerTransaction();
		model.addAttribute("partnertrans", partnerTrans);
		List<Partner> partners = partnerService.findAllPartnersList();
		model.addAttribute("partnerList",partners);
		List<Product> products = productService.findAllProducts();
		model.addAttribute("productList",products);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "addpartnertrans";
	}
	
	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/newPartnerTrans" }, method = RequestMethod.POST)
	public String savePartnerTrans(@Valid PartnerTransaction trans, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "addpartnertrans";
		}
		
		partnerTransService.savePartnerTrans(trans);

		model.addAttribute("success", "Partner Trans " + trans.getId() +" saved successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "addpartnersuccess";
	}
	
	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-partnertrans-{id}" }, method = RequestMethod.GET)
	public String editPartnerTrans(@PathVariable String id, ModelMap model) {
		PartnerTransaction partnerTransaction = partnerTransService.findById(Integer.parseInt(id));
		model.addAttribute("partnertrans", partnerTransaction);
		List<Partner> partners = partnerService.findAllPartnersList();
		model.addAttribute("partnerList",partners);
		List<Product> products = productService.findAllProducts();
		model.addAttribute("productList",products);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "addpartnertrans";
	}
	

}
