package com.project.service;

import java.util.ArrayList;
import java.util.List;

import com.project.DTO.FriendsDTO;
import com.project.domain.User;

public interface UserService {
	User login(String email, String password);
	User register(User u, String loginType);
	User changeUser(User u);
	List<User> getCTadmins();
	List<User> getUsers(User u);
	ArrayList<User> getFriends(User u);

	ArrayList<User> getFriendRequest(User u);
	boolean addFriend(FriendsDTO u);
	boolean declineRequest(FriendsDTO u);
	boolean deleteFriend(FriendsDTO u);
	ArrayList<User> acceptRequest(FriendsDTO u);
}
