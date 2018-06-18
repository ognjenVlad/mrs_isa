package com.project.service;

import java.util.ArrayList;
import java.util.List;

import com.project.DTO.FriendsDTO;
import com.project.DTO.ScaleDTO;
import com.project.domain.User;
import com.project.utils.Response;

public interface UserService {
	User login(String email, String password);
	User register(User u, String loginType);
	User changeUser(User u);
	List<User> getCTadmins();
	List<User> getUsers(User u);
	ArrayList<User> getFriends(User u);
	Response activate_admin(String email,String pw);
	Response setScale(ScaleDTO scale);

	ArrayList<User> getFriendRequest(User u);
	boolean addFriend(FriendsDTO u);
	boolean declineRequest(FriendsDTO u);
	boolean deleteFriend(FriendsDTO u);
	ArrayList<User> acceptRequest(FriendsDTO u);
}
