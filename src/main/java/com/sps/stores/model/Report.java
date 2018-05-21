package com.sps.stores.model;

public class Report {
	
	String reportDate;
	double saleAmount;
	double expenses;
	double dueAmount;
	double profitLoss;
	/**
	 * @return the reportDate
	 */
	public String getReportDate() {
		return reportDate;
	}
	/**
	 * @param reportDate the reportDate to set
	 */
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	/**
	 * @return the saleAmount
	 */
	public double getSaleAmount() {
		return saleAmount;
	}
	/**
	 * @param saleAmount the saleAmount to set
	 */
	public void setSaleAmount(double saleAmount) {
		this.saleAmount = saleAmount;
	}
	/**
	 * @return the expenses
	 */
	public double getExpenses() {
		return expenses;
	}
	/**
	 * @param expenses the expenses to set
	 */
	public void setExpenses(double expenses) {
		this.expenses = expenses;
	}
	/**
	 * @return the dueAmount
	 */
	public double getDueAmount() {
		return dueAmount;
	}
	/**
	 * @param dueAmount the dueAmount to set
	 */
	public void setDueAmount(double dueAmount) {
		this.dueAmount = dueAmount;
	}
	/**
	 * @return the profitLoss
	 */
	public double getProfitLoss() {
		return profitLoss;
	}
	/**
	 * @param profitLoss the profitLoss to set
	 */
	public void setProfitLoss(double profitLoss) {
		this.profitLoss = profitLoss;
	}

}
