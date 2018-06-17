package com.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.project.DTO.FriendsDTO;
import com.project.domain.Friends;
import com.project.domain.User;
import com.project.repository.FriendsRepository;
import com.project.repository.UserRepository;
import com.project.utils.Response;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FriendsRepository friendsRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;

	/*
	 * Koriscenje klase za ocitavanje vrednosti iz application.properties fajla
	 */
	@Autowired
	private Environment env;
	
	public User login(String email, String password){
		User u = userRepository.findByEmail(email);
		System.out.println(u);
		if(u==null){
			return null;
		}
		
		if(u.getEmail().equals(email) && u.getPassword().equals(password) && u.isActivated() == true){
			System.out.println(u.getEmail());
			return u;
		}else if(u.getEmail().equals(email) && u.getPassword().equals(password) && u.isActivated() == false && !u.getUser_type().equals("user")){
			return u;
		}else{
			return null;
		}
	}
	
	@Async
	public void sendMail(User user, String jwtToken) throws MailException, InterruptedException {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Confirmation mail");
		mail.setText("Hello, " + user.getName() + " thanks for singing up to our site, please click link to verify your email!"
				+ "\nhttp://localhost:8080/guest/confirm/?token="+jwtToken);
		javaMailSender.send(mail);

		System.out.println("Email poslat!");
	}
	public User register(User u, String loginType){
		User old_user = userRepository.findByEmail(u.getEmail());
		
		if(old_user != null && loginType.equals("regular")){
			return null;
		}else{
			if(loginType == "regular"){
				u.setActivated(false);
			}else{
				u.setActivated(true);
			}
			
			userRepository.save(u);
			return u;
		}
	}
	
	public User activate(String u){
		User old_user = userRepository.findByEmail(u);
		
		if(old_user == null){
			
			return null;
		}else{
			old_user.setActivated(true);
			userRepository.save(old_user);
			return old_user;
		}
	}
	
	public List<User> getUsers(User user){
		List<User> users = userRepository.findAll();
		ArrayList<User> ret = new ArrayList<User>();
		for(User u : users){
			if(user.getEmail().equals(u.getEmail()) || !u.isActivated()){
				continue;
			}
			u.setPassword("");
			ret.add(u);
		}
		return ret;
	}
	public User changeUser(User u){
		User old_user = userRepository.findById(u.getId());
		old_user.setCity(u.getCity());
		old_user.setName(u.getName());
		old_user.setPhone(u.getPhone());
		old_user.setPicture(u.getPicture());
		old_user.setSurname(u.getSurname());
		old_user.setPassword(u.getPassword());
		
		userRepository.save(u);
		return u;
	}
	
	public List<User> getCTadmins(){
		List<User> admins = new ArrayList<User>();
		List<User> users = userRepository.findAll();
		for (User user : users) {
			admins.add(user);
		}
		return admins;
	}
	
	public ArrayList<User> getFriends(User u){

		User user = userRepository.findByEmail(u.getEmail());

		ArrayList<Friends> friends = friendsRepository.findByUserAndAccepted(user,true);
		ArrayList<Friends> friends2 = friendsRepository.findByFriendAndAccepted(user,true);
		ArrayList<User> ret = new ArrayList<User>();
		for (Friends friend : friends) {
			ret.add(friend.getFriend());
		}
		for (Friends friend : friends2) {
			ret.add(friend.getUser());
		}
		return ret;
	}
	
	public boolean addFriend(FriendsDTO u){
	
		User user = userRepository.findByEmail(u.getUser().getEmail());
		User friend = userRepository.findByEmail(u.getFriend().getEmail());
		Friends f = new Friends();
		f.setFriend(friend);
		f.setUser(user);
		f.setAccepted(false);
		friendsRepository.save(f);
		return true;
	}
	
	public ArrayList<User> acceptRequest(FriendsDTO u){
		User user = userRepository.findByEmail(u.getUser().getEmail());
		User friend = userRepository.findByEmail(u.getFriend().getEmail());
		Friends f = friendsRepository.findByUserAndFriend(friend,user);
		System.out.println(user.getEmail());
		System.out.println(friend.getEmail());
		f.setAccepted(true);
		friendsRepository.save(f);
		friendsRepository.flush();
		return getFriends(user);
	}
	

	public boolean deleteFriend(FriendsDTO u){
		User user = userRepository.findByEmail(u.getUser().getEmail());
		User friend = userRepository.findByEmail(u.getFriend().getEmail());
		Friends f = friendsRepository.findByUserAndFriend(user,friend);
		friendsRepository.delete(f);
		return true;
	}
	public ArrayList<User> getFriendRequest(User u){
		System.out.println(u.getEmail());
		ArrayList<Friends> friends = friendsRepository.findByFriendAndAccepted(userRepository.findByEmail(u.getEmail()),false);
		ArrayList<User> ret = new ArrayList<User>();
		System.out.println("AAA "+ friends.size());
		for (Friends friend : friends) {
			System.out.println(friend.getFriend().getEmail());
			ret.add(friend.getUser());
		}
		return ret;
	}

	@Override
	public boolean declineRequest(FriendsDTO u) {
		User user = userRepository.findByEmail(u.getUser().getEmail());
		User friend = userRepository.findByEmail(u.getFriend().getEmail());
		Friends f = friendsRepository.findByUserAndFriend(friend,user);
		friendsRepository.delete(f);
		return true;
	}

	@Override
	public Response activate_admin(String email, String pw) {
		User user = userRepository.findByEmail(email);
		if(user == null) {
			return new Response("Wrong action",null);
		}
		user.setPassword(pw);
		user.setActivated(true);
		return new Response("Success",null);
	}
}
