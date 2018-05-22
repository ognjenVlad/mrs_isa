package com.project.service;

import java.util.ArrayList;

import com.project.DTO.FriendsDTO;
import com.project.DTO.ReservationDTO;
import com.project.domain.User;

public interface ReservationService {

	boolean makeReservation(ReservationDTO reservation);
	boolean acceptInvite(ReservationDTO reservation);
	boolean declineInvite(ReservationDTO reservation);
	ArrayList<String> findBookedSeats(String projection, String date, String time);
}
