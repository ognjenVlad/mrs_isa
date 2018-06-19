package com.project.repository;

import java.util.ArrayList;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.project.domain.Projection;

public interface ProjectionRepository extends JpaRepository<Projection, Long>{
	
	Projection save(Projection prj);
	
	Projection findById(Long id);
	
	ArrayList<Projection> findAll();
	
	@Transactional
	void deleteById(Long id);
}
