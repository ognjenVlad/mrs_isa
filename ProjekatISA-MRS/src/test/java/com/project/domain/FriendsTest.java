package com.project.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class FriendsTest {
	Friends i;
	@Before
    public void setUp()
    {
        User u = new User();
        u.setEmail("email@com");
        User friend = new User();
        u.setEmail("friend@com");
        i.setFriend(friend);
        i.setUser(u);
    }
	@Test
    public void testFriendsUser()
    {
        assertEquals("email@com", i.getUser().getEmail());
    }
	@Test
    public void testFriends()
    {
        assertEquals("cinema", i.getFriend().getEmail());
    }

}
