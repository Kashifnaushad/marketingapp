package com.marketing.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.marketing.data.LeadData;
import com.marketing.entities.Lead;
import com.marketing.services.LeadService;
import com.marketing.util.EmailService;
@Controller
public class LeadController {
	
@Autowired	
	private LeadService leadService;
	
	@Autowired
	private EmailService emailService;
	
	//http://localhost:8080/create
	@RequestMapping("/create")
	public String viewCreateLeadForm() {
		return "create_lead";
	}
	//http://localhost:8080/saveLead
//	@RequestMapping("/saveLead")
//	public String saveLead(@ModelAttribute("lead")Lead lead) {
//		leadService.saveOneLead(lead);
//		
//		
//		return"create_lead";
//	}
//second way to read form data.
	//http://localhost:8080/saveLead
	//@RequestMapping("/saveLead")
	//public String saveLead(@RequestParam("firstName")String firstName,@RequestParam("lastName")String lastName,@RequestParam("email")String email, @RequestParam("mobile")int mobile) {
		//System.out.println(firstName);
	//System.out.println(lastName);
		//System.out.println(email);
		//System.out.println(mobile);
		
		//return"create_lead";
	//}
	
	//http://localhost:8080/saveLead
	//@RequestMapping("/saveLead")
	//public String saveLead(@RequestParam("firstName")String firstName, @RequestParam("lastName")String lastName,@RequestParam("email")String email,@RequestParam("mobile")int mobile) {
		//Lead lead = new Lead();
		//lead.setFirstName(firstName);
		//lead.setLastName(lastName);
		//lead.setEmail(email);
		//lead.setMobile(mobile);
		//leadService.saveOneLead(lead);
		
		//return "create_lead";
	//}
	
	//third way to read form data...
	
	//http://localhost:8080/saveLead
	
	//@RequestMapping("/saveLead")
	//public String saveLead(LeadData leadData) {
		//System.out.println(leadData.getFirstName());
		//System.out.println(leadData.getLastName());
		//System.out.println(leadData.getMobile());
		//System.out.println(leadData.getEmail());
		
		//return"create_lead";
	//}
	//http://localhost:8080/saveLead
//	@RequestMapping("/saveLead")
	//public String saveLead(LeadData leadData) {
		//Lead lead = new Lead();
		//lead.setFirstName(leadData.getFirstName());
		//lead.setLastName(leadData.getLastName());
		//lead.setEmail(leadData.getEmail());
		//lead.setMobile(leadData.getMobile());
		//leadService.saveOneLead(lead);
		//return"create_lead";
	//}
	//http://localhost:8080/listall
	//@RequestMapping("/listall")
	//public String listLead() {
		//List<Lead> leads = leadService.getAllLeads();
		//System.out.println(leads);
		//return"";
		
	//}
	//http://localhost:8080/saveLead
	@RequestMapping("/saveLead")
	public String saveLead(@ModelAttribute("leads")Lead lead, ModelMap model) {
		leadService.saveOneLead(lead);
		emailService.sendEmail(lead.getEmail(), "Welcome Email", "Test");
		model.addAttribute("msg", "Record is saved!!!");
        return"create_lead";
	}
	//http://localhost:8080/listall
	@RequestMapping("/listall")
	public String ListLead(ModelMap model) {
		List<Lead> leads = leadService.getAllLeads();
		model.addAttribute("leads", leads);
		return"list_leads";
	}
	@RequestMapping("/delete")
	public String deleteOneLead(@RequestParam("id") int id, ModelMap model) {
		leadService.deleteLead(id);
		List<Lead> leads = leadService.getAllLeads();
		model.addAttribute("leads", leads);
		return"list_leads";
	}
	@RequestMapping("/update")
	public String updateOneLead(@RequestParam("id")int id, ModelMap model) {
		Lead lead = leadService.getLeadById(id);
		model.addAttribute("lead", lead);
		return "update_lead";
	}
	
	@RequestMapping("/updateLead")
	public String updateLead(@ModelAttribute("lead")Lead lead, ModelMap model) {
		
		leadService.saveOneLead(lead);
		
		List<Lead> leads = leadService.getAllLeads();
		model.addAttribute("leads", leads);
		return"list_leads";
		
	}
}
