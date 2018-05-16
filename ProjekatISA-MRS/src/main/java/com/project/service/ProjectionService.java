package com.project.service;

import java.util.ArrayList;

import com.project.domain.Projection;

public interface ProjectionService {

	public void addProjection(Projection prj);
	
	public ArrayList<Projection> findAll();
	
	public Projection findById(Long id);
}
