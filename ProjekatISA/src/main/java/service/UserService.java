package service;

import DTOs.UserDTO;

public interface UserService {
	UserDTO login(String email, String password);
}
