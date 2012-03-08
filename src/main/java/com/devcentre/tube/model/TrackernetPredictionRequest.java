package com.devcentre.tube.model;

public class TrackernetPredictionRequest {

	private String station;

	private String line;

	public TrackernetPredictionRequest(String station, String line) {
		super();
		this.station = station;
		this.line = line;
	}

	public String getLine() {
		return line;
	}

	public String getStation() {
		return station;
	}

	

}
