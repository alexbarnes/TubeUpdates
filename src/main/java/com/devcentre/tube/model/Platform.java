package com.devcentre.tube.model;

import java.util.ArrayList;
import java.util.List;

public class Platform {

	private final String name;

	private List<Train> trains = new ArrayList<Train>();

	public Platform(String name) {
		super();
		this.name = name;
	}

	public List<Train> getTrains() {
		return trains;
	}
	
	public String getName() {
		return name;
	}
}
