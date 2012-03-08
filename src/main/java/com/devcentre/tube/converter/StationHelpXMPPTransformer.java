package com.devcentre.tube.converter;

import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Message.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.Header;
import org.springframework.util.StringUtils;

import com.devcentre.tube.model.Station;
import com.devcentre.tube.model.StationHelpRequest;

public class StationHelpXMPPTransformer {
	
	private static final Logger log = LoggerFactory.getLogger(StationHelpXMPPTransformer.class);
	
	public Message transform(StationHelpRequest request, @Header("xmpp_from") String from) {
		log.info("Sending XMPP help message to [" + StringUtils.split(from, "/")[0] + "].");
		
		Message message = new Message(StringUtils.split(from, "/")[0]);
		message.setLanguage("en");
		message.setType(Type.chat);
		
		StringBuffer buffer = new StringBuffer();
		
		for (Station station : request.getStations()) {
			buffer.append(station.getCode() + ":" + station.getDescription());
			buffer.append(",");
		}
		
		message.addBody(null, buffer.toString());
		return message;
	}
}
