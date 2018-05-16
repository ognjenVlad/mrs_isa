package com.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.domain.Ad;
import com.project.domain.Prop;
import com.project.repository.AdRepository;
import com.project.repository.PropRepository;

@Service
public class FanZoneImpl implements FanZoneService {
	@Autowired
	AdRepository adRepository;

	@Autowired
	PropRepository propRepository;
	
	@Override
	public void addAd(Ad ad) {
		ad.setPicture("https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=120");
		adRepository.save(ad);
	}

	@Override
	public Ad getNextAd() {
		ArrayList<Ad> ads = (ArrayList<Ad>) adRepository.findByIsPublished(false);
		for(Ad ad : ads) {
			if(!ad.isTaken()) {
				ad.setTaken(true);
				adRepository.save(ad);
				System.out.println(ad);
				return ad;
			}
		}
		return null;
	}

	@Override
	public void returnAd(Ad ad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String updateAd(boolean is_published, boolean is_taken, Long id) {
		Ad ad;
		if((ad = adRepository.getOne(id))== null){
			return "Invalid request";
		}
		ad.setIsPublished(is_published);
		ad.setTaken(is_taken);
		adRepository.save(ad);
		return "Success";
	}

	@Override
	public List<Ad> getPublishedAds() {
		return adRepository.findByIsPublished(true);
	}
	
	@Override
	public void addProp(Prop prop) {
		prop.setPicture("https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=120");
		propRepository.save(prop);
	}

	@Override
	public List<Prop> getProps() {
		return propRepository.findByIsDeleted(false);
	}

	@Override
	public String deleteProp(Long id) {
		Prop prop;
		if((prop = propRepository.getOne(id))==null) {
			return "Id doesn't exist";
		}
		prop.setDeleted(true);
		propRepository.save(prop);
		return "Success";
	}
	

}
