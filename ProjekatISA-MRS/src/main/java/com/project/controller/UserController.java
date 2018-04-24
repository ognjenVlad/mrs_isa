package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.DTO.UserDTO;
import com.project.domain.User;
import com.project.service.UserServiceImpl;

@RestController
public class UserController {
	@Autowired
	private UserServiceImpl userService;
	
	@RequestMapping(value = "/login",method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public User login(@RequestBody UserDTO u){
		System.out.println(u);
		return userService.login(u.getEmail(),u.getPassword());
		//return userService.login("Admin", "Admin");
	}
	@RequestMapping(value = "/register",method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public User register(@RequestBody User u){
		System.out.println(u);
		return userService.register(u);
		//return userService.login("Admin", "Admin");
	}
}
