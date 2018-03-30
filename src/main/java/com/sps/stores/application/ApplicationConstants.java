package com.sps.stores.application;

public enum ApplicationConstants {

	PAYMENT("Payment"),ADVANCE("Advance"),RECEIVED("Received"),OPEN("Open"),CLOSE("Close");
	
	private String value;

	private ApplicationConstants(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}
}
