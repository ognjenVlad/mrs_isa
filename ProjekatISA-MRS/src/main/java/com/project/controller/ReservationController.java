package com.project.controller;

import java.security.Key;
import java.util.ArrayList;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.project.DTO.ReservationDTO;
import com.project.domain.User;
import com.project.service.ReservationImpl;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

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
		
		Gson gson = new Gson();
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("invite");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
       
        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder()
                .setSubject(gson.toJson(r))
                .signWith(signatureAlgorithm, signingKey);
		try {
			reservationService.sendInvitation(r, builder.compact());
			reservationService.sendInfo(r);
		} catch (MailException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	@RequestMapping(value = "/accept_reservation",method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean accept(@RequestBody ReservationDTO r){
		
		return reservationService.acceptInvite(r);
	}
	@RequestMapping(value = "/decline_reservation",method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean decline(@RequestBody ReservationDTO r){
		
		return reservationService.declineInvite(r);
	}
	@RequestMapping(value = "/getReservation",method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<ReservationDTO> getReservations(@RequestBody User r){
		
		return reservationService.getReservations(r);
	}
	@RequestMapping(value = "/get_seats",method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<String> getSeats(@RequestBody ReservationDTO r){
		
		return reservationService.findBookedSeats(r.getShow(), r.getDate(), r.getTime());
	}
	
}
