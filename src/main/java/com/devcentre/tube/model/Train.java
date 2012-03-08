package com.devcentre.tube.model;

public class Train {
	
	private String destination;
	
	private String timeToPlatfrom;
	
	private String currentLocation;

	public Train(String destination, String timeToPlatfrom,
			String currentLocation) {
		super();
		this.destination = destination;
		this.timeToPlatfrom = timeToPlatfrom;
		this.currentLocation = currentLocation;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getTimeToPlatfrom() {
		return timeToPlatfrom;
	}

	public void setTimeToPlatfrom(String timeToPlatfrom) {
		this.timeToPlatfrom = timeToPlatfrom;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}
}
