package com.devcentre.tube.model;

public class Station {
	
	private final String code;
	
	private final String description;

	public Station(String code, String description) {
		super();
		this.code = code;
		this.description = description;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}
}
