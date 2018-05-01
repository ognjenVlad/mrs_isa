package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.CinemaTheatre;
import com.project.service.CinemaTheatreImpl;

@RestController
@RequestMapping(value = "/adminct")
public class AdminCTConroller {
	@Autowired
	private CinemaTheatreImpl ctService;
	
	@RequestMapping(value = "/add_cinema",method = RequestMethod.POST)
	public String addCinema(@RequestBody CinemaTheatre ct){
		System.out.println(ct.getName() + "  " + ct.getIsCinema() + "\n\n");
		ctService.addCinemaTheatre(ct);
		return "success";
		
	}
}
