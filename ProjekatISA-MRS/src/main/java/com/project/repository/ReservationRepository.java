package com.project.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Reservation;
import com.project.domain.User;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{
	Reservation save(Reservation r);
	ArrayList<Reservation> findByUser(User u);
	ArrayList<Reservation> findByPlace(String name);
	ArrayList<Reservation> findByProjectionAndDateAndTime(String projection,String date, String time);
	ArrayList<Reservation> findByDateAndPlaceAndProjectionAndTimeAndUser(String date, 
			String place, String projection, String time, User u );
}
