package com.project.controller;

import java.security.Key;

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
import com.project.DTO.UserDTO;
import com.project.domain.User;
import com.project.service.UserServiceImpl;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RequestMapping("api")
@RestController
public class UserController {
	@Autowired
	private UserServiceImpl userService;
	
	@RequestMapping(value = "/login",method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public User login(@RequestBody UserDTO u){
		System.out.println(u);
		return userService.login(u.getEmail(),u.getPassword());
		//return userService.login("Admin", "Admin");
	}
	
	@RequestMapping(value = "/register",method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public User register(@RequestBody User u){
		System.out.println(u.getEmail());
		Gson gson = new Gson();
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("login");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        UserDTO dto = new UserDTO();
        dto.setEmail(u.getEmail());
        dto.setPassword(u.getPassword());
        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder()
                .setSubject(gson.toJson(dto))
                .signWith(signatureAlgorithm, signingKey);
        System.out.println("AAAAAA");
		
			try {
				userService.sendMail(u,builder.compact());
			} catch (MailException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("poslat mail");
		
		User uu = userService.register(u,"regular");
		System.out.println(uu);
		return uu;
		
	}
	@RequestMapping(value = "/loginGmail",method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public User loginGmail(@RequestBody User u){
		System.out.println(u);
		User u1 = userService.login(u.getEmail(),u.getPassword());
		if (u1==null) {
			return userService.register(u,"gmail");
		}else{
			return u1;
		}
	}
	
	@RequestMapping(value = "/changeUser",method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public User changeUser(@RequestBody User u){
		System.out.println(u);
		return userService.changeUser(u);
		
	}
}
