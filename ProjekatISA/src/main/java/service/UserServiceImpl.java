package service;

import org.springframework.beans.factory.annotation.Autowired;

import DTOs.UserDTO;
import repository.UserRepository;

public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	public UserDTO login(String email, String password){
		UserDTO u = userRepository.findByEmail(email);
		if(u.getEmail().equals(email) && u.getPassword().equals(password)){
			return u;
		}else{
			return null;
		}
	}
}
