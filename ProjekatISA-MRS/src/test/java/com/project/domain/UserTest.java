package com.project.domain;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class UserTest {

	User u;
	
	@Before
	public void setUp(){
		ArrayList<String> h = new ArrayList<String>();
		long id = 1;
		u = new User(id,"ime","prezime","email","password","picture","phone","type",h);
				
	}
	@Test
	public void testUserName(){
		assertEquals("ime",u.getName());
	}
	@Test
	public void testUserSurname(){
		assertEquals("prezime",u.getSurname());
	}
	@Test
	public void testUserPassword(){
		assertEquals("password",u.getPassword());
	}
	@Test
	public void testUserEmail(){
		assertEquals("email",u.getEmail());
	}
	@Test
	public void testUserPicture(){
		assertEquals("picture",u.getPicture());
	}
	@Test
	public void testUserPhone(){
		assertEquals("phone",u.getPhone());
	}
	@Test
	public void testUserType(){
		assertEquals("type",u.getUser_type());
	}
	@Test
    public void testUserSetEmail() {
        u.setEmail("asdf@app.com");
        assertEquals("asdf@app.com", u.getEmail());
    }
	@Test
    public void testUserSetPassword() {
        u.setPassword("pass");
        assertEquals("pass", u.getPassword());
    }
	@Test
    public void testUserSetName() {
        u.setName("name");
        assertEquals("name", u.getName());
    }
	@Test
    public void testUserSetSurname() {
        u.setSurname("surname");
        assertEquals("surname", u.getSurname());
    }
	@Test
    public void testUserSetPicture() {
        u.setSurname("pic");
        assertEquals("pic", u.getPicture());
    }
	@Test
    public void testUserSetPhone() {
        u.setPhone("telefon");
        assertEquals("telefon", u.getPhone());
    }
	@Test
    public void testUserSetType() {
        u.setUser_type("tip");
        assertEquals("tip", u.getUser_type());
    }
	
}
