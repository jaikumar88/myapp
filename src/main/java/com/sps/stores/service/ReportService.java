package com.sps.stores.service;

import java.util.List;

import com.sps.stores.model.Report;

public interface ReportService {
	
	public List<Report> getSaleReports(String startDate,String endDate);

}
