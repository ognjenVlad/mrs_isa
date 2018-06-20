package com.project.domain;

public class Discount {

	private String place;
	private String time;
	private String date;
	private String projection;
	private boolean isCinema;
	private String seat;
	
	public Discount() {
		super();
	}
	public Discount(String place, String time, String date, String projection, boolean isCinema, String seat) {
		super();
		this.place = place;
		this.time = time;
		this.date = date;
		this.projection = projection;
		this.isCinema = isCinema;
		this.seat = seat;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getProjection() {
		return projection;
	}
	public void setProjection(String projection) {
		this.projection = projection;
	}
	public boolean isCinema() {
		return isCinema;
	}
	public void setCinema(boolean isCinema) {
		this.isCinema = isCinema;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	
	
}
