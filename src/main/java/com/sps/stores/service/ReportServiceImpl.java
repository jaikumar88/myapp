package com.sps.stores.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sps.stores.model.Report;
@Service("reportService")
@Transactional
public class ReportServiceImpl implements ReportService {

	@Override
	public List<Report> getSaleReports(String startDate, String endDate) {
		
		return null;
	}
	
	

}
