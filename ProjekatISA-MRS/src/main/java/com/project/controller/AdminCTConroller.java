package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.CinemaTheatre;
import com.project.domain.Projection;
import com.project.service.CinemaTheatreImpl;
import com.project.service.ProjectionImpl;

@RestController
@RequestMapping(value = "/adminct")
public class AdminCTConroller {
	@Autowired
	private CinemaTheatreImpl ctService;
	private ProjectionImpl prService;
		
	@RequestMapping(value = "/add_cinema",method = RequestMethod.POST)
	public String addCinema(@RequestBody CinemaTheatre ct){
		ctService.addCinemaTheatre(ct);
		return "success";
	}
	
	@RequestMapping(value = "/get_cinemas",method = RequestMethod.GET)
	public List<CinemaTheatre> getCinemas(){
		return ctService.getCinemas();
	}
	@RequestMapping(value = "/get_theatres",method = RequestMethod.GET)
	public List<CinemaTheatre> getTheatres(){
		return ctService.getTheatres();
	}
	
	@RequestMapping(value = "/add_projection", method = RequestMethod.POST)
	public String addPRojection(@RequestBody Projection pr){
		prService.addProjection(pr);
		return "success";
	}
}
