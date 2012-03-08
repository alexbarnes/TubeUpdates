package com.devcentre.tube.converter;

import javax.mail.MessagingException;

import org.springframework.integration.annotation.Header;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.util.StringUtils;

import com.devcentre.tube.model.Platform;
import com.devcentre.tube.model.PredictionSummary;
import com.devcentre.tube.model.Train;

public class PredictionSummaryToMailTransformer {
	
	private JavaMailSender mailSender;
	
	public PredictionSummaryToMailTransformer(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public MimeMailMessage transform(PredictionSummary payload, @Header("xmpp_from") String from) throws MessagingException {
		
		MimeMailMessage message = new MimeMailMessage(mailSender.createMimeMessage());
		message.setTo(StringUtils.split(from, "/")[0]);
		message.setSubject("Status");
		
		StringBuffer buffer = new StringBuffer();
		
		for (Platform platform : payload.getPlatforms()) {
			buffer.append("<table>");
			buffer.append("<tr>");
				buffer.append("<th>");
				buffer.append("Current Location");
				buffer.append("</th>");
				
				buffer.append("<th>");
				buffer.append("Destination");
				buffer.append("</th>");
				
				buffer.append("<th>");
				buffer.append("Time to Platform");
				buffer.append("</th>");
			buffer.append("</tr>");
			
			for (Train train : platform.getTrains()) {
				buffer.append("<tr>");
				buffer.append("<td>");
				buffer.append(train.getCurrentLocation());
				buffer.append("</td>");
					
				buffer.append("<td>");
				buffer.append(train.getDestination());
				buffer.append("</td>");
					
				buffer.append("<td>");
				buffer.append(train.getTimeToPlatfrom());
				buffer.append("</td>");
				buffer.append("</tr>");
			}
		
			buffer.append("</table>");
		}
		
		message.getMimeMessageHelper().setText(buffer.toString(), true);
		return message;
	}
}
