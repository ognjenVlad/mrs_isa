package com.project.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Ad implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String description;

	@JsonFormat(pattern="MM/dd/yyyy")
	@Column(nullable = false)
	private Date exp_date;
	
	@Column(nullable = false)
	private String user_email;

	@Column
	private String picture;
	
	@Column
	private boolean isPublished = false;
	
	@Column
	boolean isTaken = false;
	
	@Column
    @OneToMany(
            cascade = CascadeType.ALL, 
            orphanRemoval = true)
	private List<Bid> bids = new ArrayList<Bid>();

	Ad(){}

	public Ad(Long id, String title, String description, Date exp_date, String picture,String user_email) {
		super();
		this.id = id;
		this.user_email = user_email;
		this.title = title;
		this.description = description;
		this.exp_date = exp_date;
		this.picture = picture;
	}
	
	

	public String getTitle() {
		return title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getExp_date() {
		return exp_date;
	}

	public void setExp_date(Date exp_date) {
		this.exp_date = exp_date;
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

	public boolean isIsPublished() {
		return isPublished;
	}

	public void setIsPublished(boolean isPublished) {
		this.isPublished = isPublished;
	}

	public boolean isPublished() {
		return isPublished;
	}

	public void setPublished(boolean isPublished) {
		this.isPublished = isPublished;
	}

	public boolean isTaken() {
		return isTaken;
	}

	public void setTaken(boolean isTaken) {
		this.isTaken = isTaken;
	}

	@Override
	public String toString() {
		return "Ad [id=" + id + ", title=" + title + ", description=" + description + ", exp_date=" + exp_date
				+ ", isPublished=" + isPublished + ", isTaken=" + isTaken + "]";
	}

	public List<Bid> getBids() {
		return bids;
	}

	public void setBids(ArrayList<Bid> bids) {
		this.bids = bids;
	};
	
	
}
