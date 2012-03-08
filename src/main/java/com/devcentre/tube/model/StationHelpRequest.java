package com.devcentre.tube.model;

import java.util.ArrayList;
import java.util.List;

public class StationHelpRequest {
	
	private final List<Station> stations = new ArrayList<Station>();
	
	public List<Station> getStations() {
		return stations;
	}
}
