package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Reservation;
import com.project.domain.User;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{
	Reservation save(Reservation r);
	Reservation findByDateAndPlaceAndProjectionAndTimeAndUser(String date, 
			String place, String projection, String time, User u );
}
