package com.flipcard.model;

import java.util.HashMap;

/*
 * Blueprint of report card
 * contains getters and setters for each private data field
 */
public class reportCard {
	private HashMap<String,String> report_card=new HashMap<String,String>();

	public HashMap<String, String> getReport_card() {
		return report_card;
	}

	public void setReport_card(HashMap<String, String> report_card) {
		this.report_card = report_card;
	}
	
	
}
