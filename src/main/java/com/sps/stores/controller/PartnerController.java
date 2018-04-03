package com.sps.stores.controller;

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
import com.sps.stores.model.Partner;
import com.sps.stores.model.PartnerTransaction;

@Controller
@RequestMapping("/")
public class PartnerController extends AbstractAppController {

	
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
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "addpartnertrans";
	}
	

}
