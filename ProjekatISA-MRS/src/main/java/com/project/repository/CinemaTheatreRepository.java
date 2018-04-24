package com.project.repository;

import com.project.domain.CinemaTheatre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaTheatreRepository extends JpaRepository<CinemaTheatre,Long>{
	CinemaTheatre findByAddress(String address);
}
