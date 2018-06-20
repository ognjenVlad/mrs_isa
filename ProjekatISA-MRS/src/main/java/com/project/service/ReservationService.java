package com.project.service;

import java.util.ArrayList;

import com.project.DTO.ReservationDTO;
import com.project.domain.Reservation;
import com.project.domain.User;

public interface ReservationService {

	boolean makeReservation(ReservationDTO reservation);
	boolean acceptInvite(ReservationDTO reservation);
	boolean declineInvite(ReservationDTO reservation);
	boolean cancel(ReservationDTO reservation);
	ArrayList<ReservationDTO> getReservations(User u);
	ArrayList<ReservationDTO> history(User u);
	ArrayList<ReservationDTO> cinHistory(String cinName);
	ArrayList<ReservationDTO> getInvites(User u);
	ArrayList<String> findBookedSeats(String projection, String date, String time);
}
