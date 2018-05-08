package com.project.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.DTO.ReservationDTO;
import com.project.domain.Invited;
import com.project.domain.Reservation;
import com.project.domain.User;
import com.project.repository.InvitedRepository;
import com.project.repository.ReservationRepository;
import com.project.repository.UserRepository;
@Service
public class ReservationImpl implements ReservationService {

	@Autowired
	private ReservationRepository resRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private InvitedRepository invitedRepository;
	
	@Transactional
	public boolean makeReservation(ReservationDTO reservation){
		User u = userRepository.findByEmail(reservation.getUser().getEmail());
		
		Reservation r = new Reservation();
		r.setUser(u);
		r.setDate(reservation.getDate());
		r.setTime(reservation.getTime());
		r.setPlace(reservation.getPlace());
		r.setProjection(reservation.getShow());
		Set<Invited> friends = new HashSet<Invited>();;
		
		for (User s : reservation.getFriends()) {
			User friend = userRepository.findByEmail(s.getEmail());
			Invited i = new Invited();
			System.out.println(friend.getEmail());
			i.setUser(friend);
			i.setReservation(r);
			friends.add(i);
			invitedRepository.save(i);
		}
		r.setFriends(friends);
		resRepository.save(r);
		return true;
	}
}
