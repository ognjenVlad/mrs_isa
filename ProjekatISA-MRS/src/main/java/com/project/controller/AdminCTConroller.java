package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.AdminUser;
import com.project.domain.CinemaTheatre;
import com.project.domain.User;
import com.project.service.AdminServiceImpl;
import com.project.service.CinemaTheatreImpl;
import com.project.service.UserService;

@RestController
@RequestMapping(value = "/adminct")
public class AdminCTConroller {
	@Autowired
	private CinemaTheatreImpl ctService;
	
	@Autowired
	private AdminServiceImpl aService;
	
	@RequestMapping(value = "/add_cinema",method = RequestMethod.POST)
	public String addCinema(@RequestBody CinemaTheatre ct){
		ctService.addCinemaTheatre(ct);
		return "success";		
	}
	
	@RequestMapping(value = "/add_admin",method = RequestMethod.POST)
	public String addAdmin(@RequestBody CinemaTheatre ct){
		ctService.addCinemaTheatre(ct);
		return "success";		
	}
	
	@RequestMapping(value = "/get_ct_admins",method = RequestMethod.GET)
	public List<AdminUser> getAdmins(){
		return aService.getAdmins("ct");
	}
}
