package com.devcentre.tube.converter;

import javax.mail.MessagingException;

import org.springframework.integration.annotation.Header;
import org.springframework.mail.MailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.util.StringUtils;

import com.devcentre.tube.model.Status;
import com.devcentre.tube.model.StatusSummary;

public class StatusSummaryToMailTranformer {
	
	private JavaMailSender mailSender;
	
	public StatusSummaryToMailTranformer(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public MailMessage transform(StatusSummary summary, @Header("xmpp_from") String from) throws MessagingException {
		
		MimeMailMessage message = new MimeMailMessage(mailSender.createMimeMessage());
		message.setTo(StringUtils.split(from, "/")[0]);
		message.setSubject("Status");
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("<table>");
		
		for (Status status : summary.getStatusLines()) {
			buffer.append("<tr>");
				buffer.append("<td>");
				buffer.append(status.getLine());
				buffer.append("</td>");
				
				buffer.append("<td>");
				buffer.append(status.getStatus());
				buffer.append("</td>");
			buffer.append("</tr>");
		}
		
		message.getMimeMessageHelper().setText(buffer.toString(), true);
		
		return message;
	}
}
