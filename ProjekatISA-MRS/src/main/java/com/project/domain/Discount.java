package com.project.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Discount implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String place;
	
	@Column(nullable = false)
	private String time;
	
	@Column(nullable = false)
	private String date;

	@Column(nullable = false)
	private String projection;
	
	@Column(nullable = false)
	private boolean isCinema;
	
	@Column(nullable = false)
	private String seat;
	
	@Column(nullable = false)
	private Double price;
	
	public Discount() {
		super();
	}
	public Discount(String place, String time, String date, String projection, boolean isCinema, String seat, double price) {
		super();
		this.place = place;
		this.time = time;
		this.date = date;
		this.projection = projection;
		this.isCinema = isCinema;
		this.seat = seat;
		this.price = price;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
