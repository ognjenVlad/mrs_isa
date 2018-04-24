package com.project.service;

import com.project.domain.User;

public interface UserService {
	User login(String email, String password);
	User register(User u);
}
