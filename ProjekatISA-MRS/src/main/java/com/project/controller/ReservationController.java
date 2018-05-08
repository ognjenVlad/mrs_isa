package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.DTO.ReservationDTO;
import com.project.service.ReservationImpl;

@RequestMapping("api")
@RestController
public class ReservationController {
	@Autowired
	private ReservationImpl reservationService;
	
	
	@RequestMapping(value = "/make_reservation",method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean reserve(@RequestBody ReservationDTO r){
		System.out.println("Making reservation..");
		reservationService.makeReservation(r);
		return true;
	}
}
