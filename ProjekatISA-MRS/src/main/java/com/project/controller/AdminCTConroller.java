package com.project.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.CinemaTheatre;
import com.project.domain.Discount;
import com.project.domain.Projection;
import com.project.service.CinemaTheatreImpl;
import com.project.service.ProjectionImpl;
import com.project.utils.Response;

@RestController
@RequestMapping(value = "/adminct")
public class AdminCTConroller {
	@Autowired
	private CinemaTheatreImpl ctService;
	
	@Autowired
	private ProjectionImpl prService;
		
	@RequestMapping(value = "/add_cinema",method = RequestMethod.POST)
	public String addCinema(@RequestBody CinemaTheatre ct){
		HashMap<String, Integer> rt = new HashMap<String, Integer>();
		//rt.put("a@aa", 4);
		ct.setRatings(rt);
		
		ctService.addCinemaTheatre(ct);
		return "success";
	}
	
	@RequestMapping(value = "/modify_cinema",method = RequestMethod.POST)
	public String modifyCinema(@RequestBody CinemaTheatre ct){
		ctService.modifyCinemaTheatre(ct);
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
	public String addProjection(@RequestBody Projection pr){
		pr.setRatings(new HashMap<String,Integer>());
		System.out.println(pr.getHalls().size() +" halls size \n\n");
		prService.addProjection(pr);
		return "success";
	}
	
	@RequestMapping(value = "/modify_projection", method = RequestMethod.POST)
	public String modifyProjection(@RequestBody Projection pr){
		System.out.println(pr.getHalls().size() +" halls size MODIFY\n\n");
		prService.modifyProjection(pr);
		return "success";
	}
	
	@RequestMapping(value = "/delete_projection/{id}", method = RequestMethod.POST)
	public String deleteProjection(@PathVariable(value = "id") String id){
		Long pr_id = Long.parseLong(id);
		System.out.println(pr_id+" je id \n\n\n");
		prService.deleteProjection(pr_id);
		return "success";
	}
	
	@RequestMapping(value = "/add_discount", method = RequestMethod.POST)
	public String addDiscount(@RequestBody Discount dc){
		ctService.addDiscount(dc);
		return "success";
	}
}
