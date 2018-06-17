package com.project.service;

import java.util.ArrayList;

import com.project.domain.CinemaTheatre;

public interface CinemaTheatreService {
	
	public void addCinemaTheatre(CinemaTheatre ct);
	
	public void modifyCinemaTheatre(CinemaTheatre ct);
	
	public ArrayList<CinemaTheatre> findAll();
	
	public CinemaTheatre findById(Long id);
	
	public ArrayList<CinemaTheatre> getTheatres();
	
	public ArrayList<CinemaTheatre> getCinemas();
}
