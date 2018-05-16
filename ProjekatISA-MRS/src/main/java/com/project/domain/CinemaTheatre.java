package com.project.domain;

import java.io.Serializable;
import java.util.ArrayList;

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
	
}
