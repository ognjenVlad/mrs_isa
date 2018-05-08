package com.project.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



@Entity
public class Reservation implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(optional = false)
	private User user;
	
	@Column(nullable = false)
	private String place;
	
	@Column(nullable = false)
	private String projection;
	
	@Column(nullable = false)
	private boolean isCinema;
	
	@Column(nullable = false)
	private String date;
	
	@Column(nullable = false)
	private String time;
	
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval=true, mappedBy = "reservation")
	private Set<Invited> friends;

	public Reservation() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getProjection() {
		return projection;
	}

	public void setProjection(String show) {
		this.projection = show;
	}

	public boolean isCinema() {
		return isCinema;
	}

	public void setCinema(boolean isCinema) {
		this.isCinema = isCinema;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Set<Invited> getFriends() {
		return friends;
	}

	public void setFriends(Set<Invited> friends) {
		this.friends = friends;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
