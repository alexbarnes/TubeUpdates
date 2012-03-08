package com.devcentre.tube.model;

public class Status {

	private final String line;

	private final String status;

	public Status(String line, String status) {
		super();
		this.line = line;
		this.status = status;
	}

	public String getLine() {
		return line;
	}

	public String getStatus() {
		return status;
	}

}
