package com.sps.stores.service;

import java.util.List;

import com.sps.stores.model.SalesReport;

public interface ReportService {
	
	public List<SalesReport> getSaleReports(String startDate,String endDate);

}
