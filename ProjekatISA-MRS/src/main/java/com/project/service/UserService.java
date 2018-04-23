package com.project.service;

import com.project.DTO.UserDTO;

public interface UserService {
	UserDTO login(String email, String password);
}
