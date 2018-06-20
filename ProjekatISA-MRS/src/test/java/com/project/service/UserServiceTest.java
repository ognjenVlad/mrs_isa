package com.project.service;



import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.project.DTO.FriendsDTO;
import com.project.domain.Ad;
import com.project.domain.Friends;
import com.project.domain.User;
import com.project.repository.AdRepository;
import com.project.repository.FriendsRepository;
import com.project.repository.ScaleRepository;
import com.project.repository.UserRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	UserService userService;
	@Autowired
    UserRepository userRepository;

	
	@Autowired
	private FriendsRepository friendsRepository;
	@Autowired
	private FanZoneImpl fzService;
	@Autowired
	private AdRepository adRepository;
	@Autowired
	private ScaleRepository scaleRepository;
	
	@Before
	public void setUp() throws Exception {
		
		User user = new User();
		user.setEmail("email");
		user.setPassword("password");
		user.setName("ime");
		user.setSurname("prezime");
		user.setActivated(true);
		
		User user1 = new User();
		user1.setEmail("email1");
		user1.setPassword("password");
		user1.setName("ime");
		user1.setSurname("prezime");
		user1.setActivated(true);
		
		userRepository.save(user);
		userRepository.save(user1);
	}
	 @After
	    public void delete() throws Exception {
			
			User u = userRepository.findByEmail("email");
			User u1 = userRepository.findByEmail("email1");
			userRepository.delete(u);
			userRepository.delete(u1);
		}
	@Test
	@Transactional
	public void testUserLogin() {
		
        User userResponse = userService.login("email","password");
        assertThat(userResponse != null); 
	}
	@Test
	@Transactional
	@Rollback(true)
	public void testUserRegister() {
		User user = new User();
		user.setEmail("email");
		user.setPassword("password");
		user.setName("ime");
		user.setSurname("prezime");
		int old_size = userRepository.findAll().size();
        User userResponse = userService.register(user,"regular");
       

    	assertThat(userRepository.findAll()).hasSize(old_size);
	}
//	@Test
//	public void testChangeUser() {
//		User user = new User();
//		user.setEmail("email");
//		user.setPassword("password1");
//		user.setName("ime");
//		user.setSurname("prezime");
//		user.setCity("ime");
//		user.setPhone("prezime");
//		user.setPicture("prezime");
//        User userResponse = userService.changeUser(user);
//        assertThat(userResponse.getPassword() == "password1"); 
//	}
	@Test
	@Transactional
	public void testGetUsers() {
		User user = new User();
		user.setEmail("email");
		user.setPassword("password");
        List<User> userResponse = userService.getUsers(user);
        assertThat(userResponse).hasSize(1); 
	}
	
	
	@Test
    @Transactional
    @Rollback(true)
    public void addAdTestBad() {
    	Ad ad = new Ad(201L,"Novi oglas2","Opis",new Date(),"",null);
    	
    	int old_size = adRepository.findAll().size();
    	    	
    	fzService.addAd(ad, "bad_email@gmail.com");

    	assertThat(adRepository.findAll()).hasSize(old_size);
    }
	@Test
	@Transactional
	@Rollback(true)
	public void testAddFriend() {
		FriendsDTO f = new FriendsDTO();
		User user = new User();
		user.setEmail("email");
		user.setPassword("password");
		user.setName("ime");
		user.setSurname("prezime");
		user.setActivated(true);
		
		User user1 = new User();
		user1.setEmail("email1");
		user1.setPassword("password");
		user1.setName("ime");
		user1.setSurname("prezime");
		user1.setActivated(true);
		f.setFriend(user);
		f.setUser(user1);
		
		int old_size = friendsRepository.findAll().size();
    	
		userService.addFriend(f);

    	assertThat(friendsRepository.findAll()).hasSize(old_size+1);
	}
	@Test
	@Transactional
	@Rollback(true)
	public void testDeleteFriend() {
		FriendsDTO f = new FriendsDTO();
		User user = new User();
		user.setEmail("email");
		user.setPassword("password");
		user.setName("ime");
		user.setSurname("prezime");
		user.setActivated(true);
		
		User user1 = new User();
		user1.setEmail("email1");
		user1.setPassword("password");
		user1.setName("ime");
		user1.setSurname("prezime");
		user1.setActivated(true);
		f.setFriend(user);
		f.setUser(user1);
		userService.addFriend(f);
		int old_size = friendsRepository.findAll().size();
    	
		userService.deleteFriend(f);

    	assertThat(friendsRepository.findAll()).hasSize(old_size-1);
	}
	@Test
	@Transactional
	@Rollback(true)
	public void testGetRequests() {
		FriendsDTO f = new FriendsDTO();
		User user = new User();
		user.setEmail("email");
		user.setPassword("password");
		user.setName("ime");
		user.setSurname("prezime");
		user.setActivated(true);
		
		User user1 = new User();
		user1.setEmail("email1");
		user1.setPassword("password");
		user1.setName("ime");
		user1.setSurname("prezime");
		user1.setActivated(true);
		f.setFriend(user);
		f.setUser(user1);
		userService.addFriend(f);

    	assertThat(userService.getFriendRequest(user)).hasSize(1);
	}
	@Test
	@Transactional
	@Rollback(true)
	public void testAcceptRequest() {
		FriendsDTO f = new FriendsDTO();
		User user = new User();
		user.setEmail("email");
		user.setPassword("password");
		user.setName("ime");
		user.setSurname("prezime");
		user.setActivated(true);
		
		User user1 = new User();
		user1.setEmail("email1");
		user1.setPassword("password");
		user1.setName("ime");
		user1.setSurname("prezime");
		user1.setActivated(true);
		f.setFriend(user);
		f.setUser(user1);
		
		userService.addFriend(f);
		
		f.setFriend(user1);
		f.setUser(user);
		int old_size = friendsRepository.findAll().size();
		userService.acceptRequest(f);

    	assertThat(friendsRepository.findAll()).hasSize(old_size);
	}
}
