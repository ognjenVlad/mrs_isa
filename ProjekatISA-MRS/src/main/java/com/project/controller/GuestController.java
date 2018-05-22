package com.project.controller;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import com.project.DTO.UserDTO;
import com.project.domain.User;
import com.project.service.UserServiceImpl;
@RequestMapping("guest")
@RestController
public class GuestController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserServiceImpl userService;
	
	@RequestMapping(value = "/confirm",
            method = RequestMethod.GET)
    public String registerInDB(@RequestParam String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary("login"))
                .parseClaimsJws(token).getBody();
        Gson gson = new Gson();
        UserDTO user = gson.fromJson(claims.getSubject(), UserDTO.class);
        User retuser = userService.activate(user.getEmail());
        if (retuser == null) {
            System.out.println("User not created");
            return null;
        }

        System.out.println("User successfully created");
        return "redirect:/sigin.html";
    }
}
