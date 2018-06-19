package com.project.service;

import java.util.ArrayList;

import com.project.DTO.RateDTO;
import com.project.domain.Projection;

public interface ProjectionService {

	public void addProjection(Projection prj);
	
	public void modifyProjection(Projection prj);
	
	public void deleteProjection(Long id);
	
	public ArrayList<Projection> findAll();
	
	public Projection findById(Long id);
		
	public void updateRating(RateDTO rate);
}
