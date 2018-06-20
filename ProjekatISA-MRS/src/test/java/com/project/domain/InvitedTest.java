package com.project.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class InvitedTest {
	Invited i;
	@Before
    public void setUp()
    {
        User u = new User();
        u.setEmail("email@com");
        Reservation reservation = new Reservation();
        reservation.setPlace("cinema");
        i.setReservation(reservation);
        i.setUser(u);
    }
	@Test
    public void testInvitedUser()
    {
        assertEquals("email@com", i.getUser().getEmail());
    }
	@Test
    public void testInvitedReservation()
    {
        assertEquals("cinema", i.getReservation().getPlace());
    }
	


}
