package com.marketing.services;

import java.util.List;

import com.marketing.entities.Lead;

public interface LeadService {
	public void saveOneLead(Lead lead);

	public List<Lead> getAllLeads();

	public void deleteLead(int id);

	public Lead getLeadById(int id);

}
