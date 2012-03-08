package com.devcentre.tube.converter;

import org.springframework.integration.annotation.Header;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.util.StringUtils;

import com.devcentre.tube.model.PredictionSummary;

public class PredictionSummaryToMailTransformer {
	
	
	public MailMessage transform(PredictionSummary payload, @Header("xmpp_from") String from) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(StringUtils.split(from, "/")[0]);
		message.setSubject("Status");
		message.setText(payload.toString());
		
		return message;
	}

}
