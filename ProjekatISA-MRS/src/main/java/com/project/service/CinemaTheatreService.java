package com.project.service;

import java.util.ArrayList;

import com.project.DTO.RateDTO;
import com.project.domain.CinemaTheatre;
import com.project.utils.Response;

public interface CinemaTheatreService {
	
	public void addCinemaTheatre(CinemaTheatre ct);
	
	public void modifyCinemaTheatre(CinemaTheatre ct);
	
	public ArrayList<CinemaTheatre> findAll();
	
	public CinemaTheatre findById(Long id);
	
	public ArrayList<CinemaTheatre> getTheatres();
	
	public ArrayList<CinemaTheatre> getCinemas();
	
	public void updateRating(RateDTO rate);
	
}
