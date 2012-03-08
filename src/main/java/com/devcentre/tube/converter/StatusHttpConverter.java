package com.devcentre.tube.converter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.xml.xpath.XPathExpression;
import org.springframework.xml.xpath.XPathExpressionFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.devcentre.tube.model.Status;
import com.devcentre.tube.model.StatusSummary;

public class StatusHttpConverter implements HttpMessageConverter<StatusSummary> {
	
	private List<MediaType> supportedMediaTypes = Collections.emptyList();

	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		return StatusSummary.class.equals(clazz);
	}

	@Override
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		return false;
	}

	@Override
	public List<MediaType> getSupportedMediaTypes() {
		return supportedMediaTypes;
	}

	@Override
	public StatusSummary read(Class<? extends StatusSummary> clazz,
			HttpInputMessage inputMessage) throws IOException,
			HttpMessageNotReadableException {
		
		StatusSummary statusSummary = new StatusSummary();
		try {		
			Document document =
				DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputMessage.getBody());
			
			XPathExpression lineXp = XPathExpressionFactory.createXPathExpression("ArrayOfLineStatus/LineStatus/Line");	
			List<Node> lines = lineXp.evaluateAsNodeList(document);
			
			XPathExpression statusXp = XPathExpressionFactory.createXPathExpression("ArrayOfLineStatus/LineStatus/Status");	
			List<Node> statuses = statusXp.evaluateAsNodeList(document);
			
			int counter = 0;
			for (Node node : lines) {
				String lineName = node.getAttributes().getNamedItem("Name").getTextContent();
				String status = statuses.get(counter++).getAttributes().getNamedItem("Description").getTextContent();
				statusSummary.getStatusLines().add(new Status(lineName, status));
			}
			
		} catch (Exception e) {
			throw new HttpMessageConversionException("Failed to convert response to: " + clazz, e);
		}
		
		return statusSummary;
	}

	@Override
	public void write(StatusSummary t, MediaType contentType,
			HttpOutputMessage outputMessage) throws IOException,
			HttpMessageNotWritableException {
		
	}

}
