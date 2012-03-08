package com.devcentre.tube;

import org.junit.Assert;
import org.junit.Test;

import com.devcentre.tube.converter.XMPPInputTransformer;
import com.devcentre.tube.model.StationHelpRequest;
import com.devcentre.tube.model.TrackernetPredictionRequest;
import com.devcentre.tube.model.TrackernetStatusRequest;

/**
 * Tests for the {@linkplain XMPPInputTransformer}. Add new
 * request types in here to ensure that we're setting them
 * up correctly based on user input.
 * 
 * @author Alex Barnes
 *
 */
public class TestXMPPInputTransformer {

	@Test
	public void testHelp() {
		String request = "help W";

		XMPPInputTransformer transformer = new XMPPInputTransformer();

		Object helpRequest = transformer.transform(request);
		Assert.assertTrue("Should be help request type",
				StationHelpRequest.class.equals(helpRequest.getClass()));

		// Check the values
		StationHelpRequest stationHelp = (StationHelpRequest) helpRequest;
		Assert.assertEquals(2, stationHelp.getStations().size());
	}

	@Test
	public void testStatus() {
		String request = "status";

		XMPPInputTransformer transformer = new XMPPInputTransformer();
		Object helpRequest = transformer.transform(request);

		Assert.assertTrue("Should be status request type",
				TrackernetStatusRequest.class.equals(helpRequest.getClass()));

	}

	@Test
	public void testPrediction() {
		String requestString = "prediction s:BNK l:C";

		XMPPInputTransformer transformer = new XMPPInputTransformer();
		Object helpRequest = transformer.transform(requestString);

		Assert.assertTrue(
				"Should be status request type",
				TrackernetPredictionRequest.class.equals(helpRequest.getClass()));

		TrackernetPredictionRequest request = (TrackernetPredictionRequest) helpRequest;
		Assert.assertEquals("BNK", request.getStation());
		Assert.assertEquals("C", request.getLine());
	}

}
