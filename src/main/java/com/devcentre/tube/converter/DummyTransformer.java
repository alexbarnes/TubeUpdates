package com.devcentre.tube.converter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.devcentre.tube.model.LineStatus;


public class DummyTransformer implements HttpMessageConverter<LineStatus> {
	
	private List<MediaType> supportedMediaTypes = Collections.emptyList();

	@Override
	public boolean canRead(Class<?> arg0, MediaType arg1) {
		return LineStatus.class.equals(arg0);
	}
	
	@Override
	public boolean canWrite(Class<?> arg0, MediaType arg1) {
		return false;
	}

	@Override
	public List<MediaType> getSupportedMediaTypes() {
		return supportedMediaTypes;
	}

	@Override
	public LineStatus read(Class<? extends LineStatus> arg0,
			HttpInputMessage arg1) throws IOException,
			HttpMessageNotReadableException {
		return new LineStatus();
	}

	@Override
	public void write(LineStatus arg0, MediaType arg1, HttpOutputMessage arg2)
			throws IOException, HttpMessageNotWritableException {
		
	}

}
