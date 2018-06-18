package com.project.domain;

import java.io.Serializable;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String surname;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column
	private String picture;
	
	@Column
	private boolean activated = false;
	
	@Column
	private String city;
	
	@Column
	private String phone;
	
	@Column
	private String user_type;
	
	@Column
	private MEMBER_LEVEL member_level;
	
	@Column
	private int no_of_visits;
	
	public User() {
		super();
		this.no_of_visits = 0;
		this.member_level = MEMBER_LEVEL.NONE;
	}

	public User(Long id, String name, String surname, String email, String password, String picture,String phone,String user_type) {
		super();
		this.no_of_visits = 0;
		this.member_level = MEMBER_LEVEL.NONE;
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.picture = picture;
		this.user_type = user_type;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type; // user , sys , ct , fan
	}

	
	public int getNo_of_visits() {
		return no_of_visits;
	}

	public void setNo_of_visits(int no_of_visits) {
		this.no_of_visits = no_of_visits;
	}

	public MEMBER_LEVEL getMember_level() {
		return member_level;
	}

	public void setMember_level(MEMBER_LEVEL member_level) {
		this.member_level = member_level;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", activated=" + activated
				+ ", user_type=" + user_type + ", member_level=" + member_level + "]";
	}
	
	

}
