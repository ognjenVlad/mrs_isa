package com.project.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import javax.persistence.Column;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.project.DTO.ReservationDTO;
import com.project.domain.Reservation;
import com.project.domain.User;
import com.project.repository.InvitedRepository;
import com.project.repository.ReservationRepository;
import com.project.repository.ScaleRepository;
import com.project.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationServiceTest {
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private ReservationRepository resRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private InvitedRepository invitedRepository;

	@Autowired
	private ScaleRepository scaleRepository;
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	@Transactional
	public void testMakeReservation() {
		
		User user = new User();
		user.setEmail("email");
		user.setPassword("password");
		user.setName("ime");
		user.setSurname("prezime");
		user.setActivated(true);
		Reservation r = new Reservation("place", "show", true, "date", "time",
				"100");
		r.setPrice("100");
		r.setUser(user);
		int old_size = resRepository.findAll().size();
		resRepository.save(r);
		assertThat(resRepository.findAll()).hasSize(old_size+1);
	}

}
