package com.devcentre.tube.converter;

import org.springframework.integration.annotation.Header;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.util.StringUtils;

import com.devcentre.tube.model.StatusSummary;

public class StatusSummaryToMailTranformer {
	
	public MailMessage transform(StatusSummary summary, @Header("xmpp_from") String from) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(StringUtils.split(from, "/")[0]);
		message.setSubject("Status");
		message.setText(summary.toString());
		
		return message;
	}
}
