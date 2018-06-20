package com.project.domain;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class ReservationTest {
	Reservation i;
	@Before
    public void setUp()
    {
        User u = new User();
        u.setEmail("email@com");
        
        Set<Invited> friends = null;
        i.setUser(u);
        i.setFriends(friends);
    }
	@Test
    public void testReservationUser()
    {
        assertEquals("email@com", i.getUser().getEmail());
    }
	@Test
    public void testReservationFriends()
    {
        assertEquals(null, i.getFriends());
    }
}
