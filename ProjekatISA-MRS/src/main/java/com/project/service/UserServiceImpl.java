package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.domain.User;
import com.project.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	public User login(String email, String password){
		User u = userRepository.findByEmail(email);
		
		if(u.getEmail().equals(email) && u.getPassword().equals(password)){
			return u;
		}else{
			return null;
		}
	}
	public User register(User u){
		User old_user = userRepository.findByEmail(u.getEmail());
		
		if(old_user != null){
			return null;
		}else{
			userRepository.save(u);
			return u;
		}
	}
}
