package com.project.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.project.DTO.ReservationDTO;
import com.project.domain.Invited;
import com.project.domain.Reservation;
import com.project.domain.User;
import com.project.repository.InvitedRepository;
import com.project.repository.ReservationRepository;
import com.project.repository.UserRepository;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
@Service
public class ReservationImpl implements ReservationService {

	@Autowired
	private ReservationRepository resRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private InvitedRepository invitedRepository;
	
	@Autowired
	private Environment env;
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Transactional
	public boolean makeReservation(ReservationDTO reservation){
		User u = userRepository.findByEmail(reservation.getUser().getEmail());
		
		Reservation r = new Reservation();
		r.setUser(u);
		r.setDate(reservation.getDate());
		r.setTime(reservation.getTime());
		r.setPlace(reservation.getPlace());
		r.setProjection(reservation.getShow());
		Set<Invited> friends = new HashSet<Invited>();
		int j = 0;
		for (User s : reservation.getFriends()) {
			User friend = userRepository.findByEmail(s.getEmail());
			Invited i = new Invited();
	
			i.setUser(friend);
			i.setReservation(r);
			i.setSeat(reservation.getSeats().get(j));
			
			friends.add(i);
			j++;
			invitedRepository.save(i);
		}
		
		//mora se dodati i sediste za onog koji poziva prijatelje i ona za koja nisu pozvani prijatelji
		for(int k = j;k!= reservation.getSeats().size();k++){
			Invited i = new Invited();
			i.setUser(u);
			i.setReservation(r);
			i.setSeat(reservation.getSeats().get(k));
			friends.add(i);
		}
		r.setFriends(friends);
		resRepository.save(r);
		return true;
	}
	public boolean acceptInvite(ReservationDTO r){
		System.out.println(r.getPlace());
		User u = userRepository.findByEmail(r.getUser().getEmail());
		ArrayList<Reservation> reservations = resRepository.findByDateAndPlaceAndProjectionAndTimeAndUser(r.getDate(), r.getPlace(),
				r.getShow(),r.getTime(),u);
		if(reservations == null){
			return false;
		}
		User u1 = userRepository.findByEmail(r.getFriends().get(0).getEmail());
		for(Reservation res:reservations){
			Invited  i = invitedRepository.findByUserAndReservation(u1,res);
			if(i!=null){
				i.setAccepted(true);
				invitedRepository.save(i);
				break;
			}
			
		}
		
		
		return true;
	}
	
	public boolean declineInvite(ReservationDTO r){
		User u = userRepository.findByEmail(r.getUser().getEmail());
		ArrayList<Reservation> reservations = resRepository.findByDateAndPlaceAndProjectionAndTimeAndUser(r.getDate(), r.getPlace(),
				r.getShow(),r.getTime(),u);
		if(reservations == null){
			return false;
		}
		User u1 = userRepository.findByEmail(r.getFriends().get(0).getEmail());
		for(Reservation res:reservations){
			Invited  i = invitedRepository.findByUserAndReservation(u1,res);
			if(i!=null){
				i.setAccepted(true);
				invitedRepository.delete(i);
				break;
			}
			
		}
		return true;
	}
	public ArrayList<Reservation> history(User u){
		User user = userRepository.findByEmail(u.getEmail());
		ArrayList<Reservation> reservations= resRepository.findByUser(user);
		ArrayList<Reservation> history = new ArrayList<Reservation>();
		for(Reservation r: reservations){
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date date = null;
			try {
				date = format.parse(r.getDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Date today = java.util.Calendar.getInstance().getTime();
			if(date.after(today)){
				continue;
			}
			history.add(r);
		}
		return history;
	}
	public ArrayList<ReservationDTO> getReservations(User u){
		User user = userRepository.findByEmail(u.getEmail());
		ArrayList<Reservation> reservations= resRepository.findByUser(user);
		ArrayList<ReservationDTO> allInfo = new ArrayList<ReservationDTO>();
		for(Reservation r: reservations){
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date date = null;
			try {
				date = format.parse(r.getDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Date today = java.util.Calendar.getInstance().getTime();
			if(date.before(today)){
				continue;
			}
			ReservationDTO info = new ReservationDTO(r);
			
			ArrayList<Invited> i = invitedRepository.findByReservation(r);
			for (Invited invited : i) {
				invited.getUser().setPicture("");
				if(!invited.getUser().getEmail().equals(u.getEmail())){
					info.getFriends().add(invited.getUser());
				}
				info.getSeats().add(invited.getSeat());
			}
			allInfo.add(info);
		}
		
		return allInfo;
	}
	public ArrayList<String> findBookedSeats(String projection, String date, String time){
		ArrayList<Reservation> tickets = resRepository.findByProjectionAndDateAndTime(projection,date,time);
		ArrayList<String> seats = new ArrayList<String>();
		for(Reservation r:tickets){
			ArrayList<Invited> i = invitedRepository.findByReservation(r);
			for (Invited invited : i) {
				seats.add(invited.getSeat());
			}
		}
		return seats;
	}
	@Async
	public void sendInvitation(ReservationDTO reservation, String jwtToken) throws MailException, InterruptedException {
		String type = "";
		if(reservation.isCinema()){
			type = "Cinema";
		}else{
			type = "Theatre";
		}
		int i = 0;
		reservation.getUser().setPicture("");
		reservation.getUser().setPassword("");
		for(User u: reservation.getFriends()){
			//u invitu se u listi prijatelja samo nalazi onaj kome se mail salje
			ReservationDTO dto = new ReservationDTO(reservation);
			ArrayList<User> friends = new ArrayList<User>();
			friends.add(u);
			dto.setFriends(friends);
			
			Gson gson = new Gson();
		
	        //SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
	     
	        JwtBuilder builder = Jwts.builder()
	                .setSubject(gson.toJson(dto));
	        System.out.println();
			
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setTo("ogauzz1@gmail.com");
			mail.setFrom(env.getProperty("spring.mail.username"));
			mail.setSubject("Invitation mail");
			mail.setText("Hello, " + u.getName() + " you have been invited by "+ reservation.getUser().getName()+ " "
					+  reservation.getUser().getSurname() +"\nPlay: "+reservation.getShow()+"\nDate: "+reservation.getDate()+"\nTime: "+ reservation.getTime()+ 
					"\n"+type+": "+  reservation.getPlace() +"\n\n  Please visit link to accept/decline your inivite!"
					+ "\nhttp://localhost:8080/invite.html/?"+builder.compact());
			javaMailSender.send(mail);
			i++;
		}
		

		System.out.println("Email poslat!");
	}
	
	@Async
	public void sendInfo(ReservationDTO reservation) throws MailException, InterruptedException {
		String type = "";
		if(reservation.isCinema()){
			type = "Cinema";
		}else{
			type = "Theatre";
		}
		
		String friends = "\nInvited friends:";
		for(User u : reservation.getFriends()){
			System.out.println(u.getEmail());
			User friend = userRepository.findByEmail(u.getEmail());
			friends +="\n   "+ friend.getName() + " " + friend.getSurname();
		}
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(reservation.getUser().getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Your reservation data");
		mail.setText("Hello, " + reservation.getUser().getName() + ", your reservation:\n "+
		"\nPlay: "+reservation.getShow()+"\n\nDate: "+reservation.getDate()+"\n\nTime: "+ reservation.getTime()+ 
				"\n\n"+type+":"+  reservation.getPlace()+"\n"+friends);
		javaMailSender.send(mail);

		System.out.println("Email poslat! "+reservation.getUser().getEmail());
	}
}
