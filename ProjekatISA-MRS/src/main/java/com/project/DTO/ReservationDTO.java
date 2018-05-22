package com.project.DTO;

import java.util.ArrayList;
import java.util.List;

import com.project.domain.User;

public class ReservationDTO {

	private User user;
	
	private String place;
	
	private String show;
	
	private boolean isCinema;
	
	private String date;
	
	private String time;
	
	private ArrayList<User> friends;
	
	private ArrayList<String> seats;
	
	public ReservationDTO() {
		super();
	}

	public ReservationDTO(ReservationDTO r) {
		super();
		this.user = r.user;
		this.place = r.place;
		this.show = r.show;
		this.isCinema = r.isCinema;
		this.date = r.date;
		this.time = r.time;
		this.friends = r.friends;
		this.seats = r.seats;
	}

	public ReservationDTO(User user, String place, String show, boolean isCinema, String date, String time,
			ArrayList<User> friends) {
		super();
		this.user = user;
		this.place = place;
		this.show = show;
		this.isCinema = isCinema;
		this.date = date;
		this.time = time;
		this.friends = friends;
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

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
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

	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(ArrayList<User> friends) {
		this.friends = friends;
	}

	public ArrayList<String> getSeats() {
		return seats;
	}

	public void setSeats(ArrayList<String> seats) {
		this.seats = seats;
	}

	

}
