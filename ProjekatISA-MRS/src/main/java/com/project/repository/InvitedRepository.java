package com.project.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Invited;
import com.project.domain.Reservation;
import com.project.domain.User;

public interface InvitedRepository extends JpaRepository<Invited, Long>{
	Invited save(Invited r);
	Invited findByUserAndReservation(User u, Reservation r);
	ArrayList<Invited> findByReservation(Reservation r);
}