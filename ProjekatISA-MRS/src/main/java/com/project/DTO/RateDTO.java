package com.project.DTO;

public class RateDTO {

	private String user_id;
	private Long id;
	private Integer vote;
	
	
	public RateDTO() {
		super();
	}
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVote() {
		return vote;
	}
	
	public void setVote(Integer vote) {
		this.vote = vote;
	}
	
}
