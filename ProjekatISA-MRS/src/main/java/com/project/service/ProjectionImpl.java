package com.project.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.DTO.RateDTO;
import com.project.domain.CinemaTheatre;
import com.project.domain.Projection;
import com.project.repository.ProjectionRepository;

@Service
public class ProjectionImpl implements ProjectionService {

	@Autowired
	ProjectionRepository prRepository;
	
	@Override
	public void addProjection(Projection prj) {
		System.out.println("U POJECTIONIMP JE " + prj.getName() + "\n\n");
		prRepository.save(prj);
	}
	
	@Override
	public void modifyProjection(Projection prj){
		System.out.println("U PROJECTIONIMP JE " + prj.getName() + "\n\n");
		Projection old_prj = prRepository.findById(prj.getId());
		
		old_prj.setName(prj.getName());
		old_prj.setActors(prj.getActors());
		old_prj.setGenre(prj.getGenre());
		old_prj.setDirector(prj.getDirector());
		old_prj.setDescription(prj.getDescription());
		old_prj.setHalls(prj.getHalls());
		old_prj.setTime(prj.getTime());
		old_prj.setLength(prj.getLength());
		if (prj.getPoster() != null)
			old_prj.setPoster(prj.getPoster());
		old_prj.setPrice(prj.getPrice());
		prRepository.save(old_prj);
	}
	
	@Override
	public void deleteProjection(Long id){
		System.out.println("OVDE JE ID "+id+"\n\n");
		prRepository.deleteById(id);
		return;
	}

	@Override
	public ArrayList<Projection> findAll() {
		return prRepository.findAll();
	}

	@Override
	public Projection findById(Long id) {
		return prRepository.findById(id);
	}
	
	@Override
	public void updateRating(RateDTO rate){
		Projection pr = prRepository.findById(rate.getId());
		HashMap<String, Integer> ratings = pr.getRatings();
		ratings.put(rate.getUser_id(), rate.getVote());
		pr.setRatings(ratings);
		prRepository.save(pr);
		return;
	}

}
