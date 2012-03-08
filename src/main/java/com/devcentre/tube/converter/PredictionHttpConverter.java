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

import com.devcentre.tube.model.Platform;
import com.devcentre.tube.model.PredictionSummary;
import com.devcentre.tube.model.Train;

public class PredictionHttpConverter implements HttpMessageConverter<PredictionSummary> {
	
	private List<MediaType> supportedMediaTypes = Collections.emptyList();

	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		return PredictionSummary.class.equals(clazz);
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
	public PredictionSummary read(Class<? extends PredictionSummary> clazz,
			HttpInputMessage inputMessage) throws IOException,
			HttpMessageNotReadableException {
		
		PredictionSummary summary = new PredictionSummary();
		try {		
			Document document =
				DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputMessage.getBody());
			
			XPathExpression platformXp = XPathExpressionFactory.createXPathExpression("//S/P");	
			List<Node> platforms = platformXp.evaluateAsNodeList(document);
			
			// Work through the platforms
			for (Node node : platforms) {
				Platform platform = new Platform(node.getAttributes().getNamedItem("N").getTextContent());
				summary.getPlatforms().add(platform);
				
				// Now build the trains
				XPathExpression trainXp = XPathExpressionFactory.createXPathExpression("T");	
				List<Node> trains = trainXp.evaluateAsNodeList(node);
				
				for (Node trainNode : trains) {
					String destination = trainNode.getAttributes().getNamedItem("Destination").getTextContent();
					String timeToPlatfrom = trainNode.getAttributes().getNamedItem("TimeTo").getTextContent();
					String currentLocation = trainNode.getAttributes().getNamedItem("Location").getTextContent(); 
					
					platform.getTrains().add(new Train(destination, timeToPlatfrom, currentLocation));
				}
			}
			
		} catch (Exception e) {
			throw new HttpMessageConversionException("Failed to convert response to: " + clazz, e);
		}
		
		return summary;
	}

	@Override
	public void write(PredictionSummary t, MediaType contentType,
			HttpOutputMessage outputMessage) throws IOException,
			HttpMessageNotWritableException {
		
	}

}
