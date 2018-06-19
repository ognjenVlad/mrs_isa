package com.project.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.DTO.RateDTO;
import com.project.domain.CinemaTheatre;
import com.project.repository.CinemaTheatreRepository;
import com.project.repository.UserRepository;
import com.project.utils.Response;

@Service
public class CinemaTheatreImpl implements CinemaTheatreService{

	@Autowired
	CinemaTheatreRepository ctRepository;
		
	@Override
	public void addCinemaTheatre(CinemaTheatre ct) {
//		if(ctRepository.findByAddress(ct.getAddress()) == null)
//			System.out.println(ct.getHalls().get(0).getHall_id() +"\n\n");
		ctRepository.save(ct);
	}
	
	@Override
	public void modifyCinemaTheatre(CinemaTheatre ct){
		CinemaTheatre old_ct = ctRepository.findById(ct.getId());
		old_ct.setName(ct.getName());
		old_ct.setAddress(ct.getAddress());
		old_ct.setDescription(ct.getDescription());
		old_ct.setIsCinema(ct.getIsCinema());
		old_ct.setHalls(ct.getHalls());
		old_ct.setRatings(ct.getRatings());
		ctRepository.save(old_ct);
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
		System.out.println(l1);
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
	
	@Override
	public void updateRating(RateDTO rate){
		CinemaTheatre ct = ctRepository.findById(rate.getId());
		HashMap<String, Integer> ratings = ct.getRatings();
		ratings.put(rate.getUser_id(), rate.getVote());
		ct.setRatings(ratings);
		ctRepository.save(ct);
		return;
	}
}
