package com.project.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.domain.CinemaTheatre;
import com.project.repository.CinemaTheatreRepository;

@Service
public class CinemaTheatreImpl implements CinemaTheatreService{

	@Autowired
	CinemaTheatreRepository ctRepository;
	
	@Override
	public void addCinemaTheatre(CinemaTheatre ct) {
		if(ctRepository.findByAddress(ct.getAddress()) == null)
			//System.out.println(ct.getName() + "  " + ct.getIsCinema() + "\n\n");
			ctRepository.save(ct);
	}
	
	@Override
	public CinemaTheatre findById(Long id){
		return ctRepository.findById(id);
	}
	
	@Override
	public ArrayList<CinemaTheatre> findAll(){
		return ctRepository.findAll();
	}
	
	@Override
	public ArrayList<CinemaTheatre> getCinemas(){
		ArrayList<CinemaTheatre> l1 = ctRepository.findAll();
		ArrayList<CinemaTheatre> cinemas = new ArrayList<CinemaTheatre>();
		for (CinemaTheatre cinemaTheatre : l1) {
			System.out.println(cinemaTheatre);
			if(cinemaTheatre.getIsCinema()){
				cinemas.add(cinemaTheatre);
			}
		}
		return cinemas;
	}
	@Override
	public ArrayList<CinemaTheatre> getTheatres(){
		ArrayList<CinemaTheatre> l1 = ctRepository.findAll();
		
		ArrayList<CinemaTheatre> theatres = new ArrayList<CinemaTheatre>();
		for (CinemaTheatre cinemaTheatre : l1) {
			if(!cinemaTheatre.getIsCinema()){
				theatres.add(cinemaTheatre);
			}
		}
		return theatres;
	}
	
}
