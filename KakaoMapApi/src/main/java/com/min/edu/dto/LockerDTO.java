package com.min.edu.dto;

import java.io.Serializable;

public class LockerDTO implements Serializable{


	private static final long serialVersionUID = -4350733981894331377L;
	private String title;
	private Double lat;
	private Double lng;
	
	public LockerDTO() {
	}

	@Override
	public String toString() {
		return "LockerDTO [title=" + title + ", lat=" + lat + ", lng=" + lng + "]";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}
	
	
}
