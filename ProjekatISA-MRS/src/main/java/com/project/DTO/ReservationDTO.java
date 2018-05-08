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
	
	private List<User> friends;

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

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

	

}
