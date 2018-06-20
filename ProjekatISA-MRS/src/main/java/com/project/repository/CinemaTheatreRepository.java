package com.project.repository;

import com.project.domain.CinemaTheatre;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaTheatreRepository extends JpaRepository<CinemaTheatre,Long>{
	
	CinemaTheatre findByAddress(String address);
	
	CinemaTheatre findById(Long id);
	
	CinemaTheatre findByName(String name);
	
	ArrayList<CinemaTheatre> findAll();
	
	CinemaTheatre save(CinemaTheatre ct);
}
