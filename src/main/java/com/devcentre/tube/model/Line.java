package com.devcentre.tube.model;

public class Line {

	private String colour;

	private String code;

	private String name;

	public Line(String colour, String code, String name) {
		super();
		this.colour = colour;
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getColour() {
		return colour;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			return ((Line) obj).getCode().equals(code);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return code.hashCode();
	}

}
