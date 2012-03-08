package com.devcentre.tube.converter;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.devcentre.tube.model.StationHelpRequest;
import com.devcentre.tube.model.StationsByLine;
import com.devcentre.tube.model.TrackernetPredictionRequest;
import com.devcentre.tube.model.TrackernetStatusRequest;

public class XMPPInputTransformer {
	
	private static final Logger log = LoggerFactory.getLogger(XMPPInputTransformer.class);
	
	/**
	 * Create a TFL compatible request from the user input.
	 * 
	 * <p>Convert:
	 * <ul>
	 * <li>Translate the method to a URL</li>
	 * <li>Translate the Station to a valid station</li>
	 * <li>Translate the line to a valid line</li>
	 * </p>
	 * 
	 * <p>An example input is summary station:bank line:central</p>
	 * 
	 * @param input the input string to be processed
	 * @return a TFL compatible request
	 */
	public Object transform(String input) {
		String[] split = StringUtils.split(input, " ");
		
		// Single provided argument (cannot be split) which is the word 'status' 
		// - return status of all lines
		if (split == null && input.equals("status")) {
			log.info("Request [" + input + "] resulted in a status request");
			return new TrackernetStatusRequest();
		}
		
		// Two provided elements and the second is help
		// The first argument should be a line
		if (split.length == 2 && split[0].equals("help")) {
			log.info("Request [" + input + "] resulted in a Help request for line [" + split[1] + "].");
			StationHelpRequest stationHelpRequest = new StationHelpRequest();
			stationHelpRequest.getStations().addAll(StationsByLine.stationsByLine.get(split[1]));
			return stationHelpRequest;
		}
		
		if (split[0].equals("prediction") && split.length == 3) {
			String station = transformStation(split[1]);
			String line = transformLine(split[2]);
			
			log.info("Request [" + input + "] resulted in a prediction request for station [" + station + "] and line [" + line + "]");
			return new TrackernetPredictionRequest(station, line);
		}
		
		throw new IllegalArgumentException("Could not create a valid Trackernet request from the supplied request");
	}
	
	
	private String transformStation(String station) {
		return StringUtils.split(station, ":")[1];
	}
	
	
	private String transformLine(String line) {
		return StringUtils.split(line, ":")[1];
	}
}
