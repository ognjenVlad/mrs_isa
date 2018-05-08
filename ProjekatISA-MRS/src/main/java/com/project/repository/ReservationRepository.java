package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{
	Reservation save(Reservation r);
}
