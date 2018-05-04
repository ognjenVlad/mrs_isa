package com.project.service;

import java.util.List;

import com.project.domain.User;

public interface UserService {
	User login(String email, String password);
	User register(User u, String loginType);
	User changeUser(User u);
	List<User> getCTadmins();
}
