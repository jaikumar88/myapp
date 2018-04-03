package com.sps.stores.application;

public enum ApplicationConstants {

	PAYMENT("Payment"),ADVANCE("Advance"),RECEIVED("Received"),OPEN("Open"),CLOSE("Close"),PARTNER_ID("partnerId");
	
	private String value;

	private ApplicationConstants(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}
}
