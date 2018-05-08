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

import com.project.domain.User;
import com.project.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
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
		}else{
			return null;
		}
	}
	
	@Async
	public void sendMail(User user, String jwtToken) throws MailException, InterruptedException {

		//Simulacija duze aktivnosti da bi se uocila razlika
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
	public User changeUser(User u){
		User old_user = userRepository.findByEmail(u.getEmail());
		userRepository.delete(old_user);
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
}
