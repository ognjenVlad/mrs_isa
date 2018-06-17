package com.project.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.project.DTO.RateDTO;
import com.project.domain.CinemaTheatre;
import com.project.domain.Projection;
import com.project.service.CinemaTheatreImpl;
import com.project.service.ProjectionImpl;

@RestController
public class GreetingController {
	@Autowired
	private CinemaTheatreImpl ctService;
	
	@Autowired
	private ProjectionImpl prService;
	
//    @GetMapping("")
//    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
//        model.addAttribute("name", name);
//        return "pocetna";
//    }

    @RequestMapping(value = "/load_cinemas",method = RequestMethod.GET)
	public ArrayList<CinemaTheatre> getCinemas(){
		ArrayList<CinemaTheatre> cinemas = ctService.findAll();
		System.out.println(cinemas);
//		for (CinemaTheatre cin : cinemas){
//			System.out.println(cin.getName()+" "+cin.getAddress()+" " + cin.getIsCinema() + "\n");
//		}
		return cinemas;
    }
    
    @RequestMapping(value = "/get_cinema", method = RequestMethod.GET)
    public CinemaTheatre getCinema(@RequestParam(name="id", required=false) Long id){
    	System.out.println(id);
    	CinemaTheatre ct = ctService.findById(id);
    	//System.out.println("Evo ga:" + ct + "\n\n");
    	return ct;
    }
    
    @RequestMapping(value = "/get_projections", method = RequestMethod.GET)
    public ArrayList<Projection> getProjections(){
    	ArrayList<Projection> projections = prService.findAll();
    	return projections;
    }
    
    @RequestMapping(value = "/rate_cinthe", method = RequestMethod.POST)
    public CinemaTheatre rateCinema(@RequestBody RateDTO rate){
    	ctService.updateRating(rate);
    	return ctService.findById(rate.getId());
    }
		
    @RequestMapping(value = "/rate_proj", method = RequestMethod.POST)
    public Projection rateProjection(@RequestBody RateDTO rate){
    	prService.updateRating(rate);
    	return prService.findById(rate.getId());
    }
}