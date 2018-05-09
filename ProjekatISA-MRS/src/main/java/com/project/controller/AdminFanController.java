package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.Ad;
import com.project.service.FanZoneImpl;

@RestController
@RequestMapping(value = "/admin_fan")
public class AdminFanController {
	@Autowired
	private FanZoneImpl fanZoneService;

	@RequestMapping(value = "/add_ad",method = RequestMethod.POST)
	public String addAd(@RequestBody Ad ad){
		fanZoneService.addAd(ad);
		return "success";
	}
}
