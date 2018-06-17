package com.project.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CinemaTheatre implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private boolean isCinema;

	@Column
	private ArrayList<Hall> halls;
	
	@Column(nullable = true)
	private HashMap<String, Double> ratings;
	
	public CinemaTheatre() {
		
	}

	public CinemaTheatre(Long id, String name, String address, String description, boolean isCinema,
			ArrayList<Hall> halls) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;
		this.isCinema = isCinema;
		this.halls = halls;
		this.ratings = new HashMap<String, Double>();
	}

	public ArrayList<Hall> getHalls() {
		return halls;
	}

	public void setHalls(ArrayList<Hall> halls) {
		this.halls = halls;
	}

	public void setCinema(boolean isCinema) {
		this.isCinema = isCinema;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getIsCinema() {
		return isCinema;
	}

	public void setIsCinema(boolean isCinema) {
		this.isCinema = isCinema;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public HashMap<String, Double> getRatings() {
//		if (ratings == null){
//			ratings = new HashMap<String, Double>();
//		}
		return ratings;
	}

	public void setRatings(HashMap<String, Double> ratings) {
		this.ratings = ratings;
	}
	
	public double getRating(){
		if (this.ratings.size() == 0){
			return 0.0;
		}
		double rating = 0.0;
		for (Map.Entry<String, Double> entry : this.ratings.entrySet()){
			rating += entry.getValue();
		}
		rating = rating / this.ratings.size();
		return rating;
	}
}
