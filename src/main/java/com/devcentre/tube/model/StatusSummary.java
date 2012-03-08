package com.devcentre.tube.model;

import java.util.ArrayList;
import java.util.List;


public class StatusSummary {
	
	private final List<Status> statusLines = new ArrayList<Status>();
	
	public List<Status> getStatusLines() {
		return statusLines;
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		
		for (Status status : statusLines) {
			buffer.append(status.getLine() + ":" + status.getStatus());
			buffer.append(",");
		}
		
		return buffer.toString();
	}
}
