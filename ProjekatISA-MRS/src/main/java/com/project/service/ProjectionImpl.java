package com.project.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public ArrayList<Projection> findAll() {
		return prRepository.findAll();
	}

	@Override
	public Projection findById(Long id) {
		return prRepository.findById(id);
	}

}
