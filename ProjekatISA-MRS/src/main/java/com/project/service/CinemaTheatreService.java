package com.project.service;

import java.util.ArrayList;

import com.project.domain.CinemaTheatre;

public interface CinemaTheatreService {
	
	public void addCinemaTheatre(CinemaTheatre ct);
	
	public ArrayList<CinemaTheatre> findAll();
}
