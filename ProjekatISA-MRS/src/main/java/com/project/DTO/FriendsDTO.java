package com.project.DTO;

import com.project.domain.User;

public class FriendsDTO {

	private User user;
	private User friend;
	
	public FriendsDTO() {
		super();
	}
	public FriendsDTO(User user, User friend) {
		super();
		this.user = user;
		this.friend = friend;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getFriend() {
		return friend;
	}
	public void setFriend(User friend) {
		this.friend = friend;
	}
}
