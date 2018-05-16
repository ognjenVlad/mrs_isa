package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.Ad;
import com.project.domain.Prop;
import com.project.service.FanZoneImpl;
import com.project.utils.Response;

@RestController
@RequestMapping(value = "/admin_fan")
public class AdminFanController {
	@Autowired
	private FanZoneImpl fanZoneService;

	@RequestMapping(value = "/add_ad", method = RequestMethod.POST)
	public String addAd(@RequestBody Ad ad) {
		fanZoneService.addAd(ad);
		return "success";
	}

	@RequestMapping(value = "/get_next_ad", method = RequestMethod.GET)
	public Response getNextAd() {
		Ad ad = fanZoneService.getNextAd();
		Response res;
		if (ad == null) {
			res = new Response("No pendings ad", null);
		} else {
			res = new Response("Success", ad);
		}
		return res;
	}

	@RequestMapping(value = "/update_ad/{is_published}/{is_taken}/{id}", method = RequestMethod.GET)
	public Response updateAd(@PathVariable(value = "is_published") String is_published,
			@PathVariable(value = "is_taken") String is_taken, @PathVariable(value = "id") String id) {
		return new Response(fanZoneService.updateAd(Boolean.parseBoolean(is_published),Boolean.parseBoolean(is_taken), Long.parseLong(id)),null);
	}

	@RequestMapping(value = "/get_ads", method = RequestMethod.GET)
	public Response getAds() {
		return new Response("Success",fanZoneService.getPublishedAds());
	}

	@RequestMapping(value = "/get_props", method = RequestMethod.GET)
	public Response getProps() {
		return new Response("Success",fanZoneService.getProps());
	}

	@RequestMapping(value = "/add_prop", method = RequestMethod.POST)
	public Response propAd(@RequestBody Prop prop) {
		fanZoneService.addProp(prop);
		return new Response("Success",null);
	}

	@RequestMapping(value = "/remove_prop/{id}", method = RequestMethod.PUT)
	public Response removeProp(@PathVariable(value = "id") String id){
		return new Response(fanZoneService.deleteProp(Long.parseLong(id)),null);
	}
}
