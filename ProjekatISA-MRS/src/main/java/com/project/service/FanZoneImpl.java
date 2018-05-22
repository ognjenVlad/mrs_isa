package com.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.domain.Ad;
import com.project.domain.Bid;
import com.project.domain.Prop;
import com.project.repository.AdRepository;
import com.project.repository.BidRepository;
import com.project.repository.PropRepository;

@Service
public class FanZoneImpl implements FanZoneService {
	@Autowired
	AdRepository adRepository;

	@Autowired
	BidRepository bidRepository;

	@Autowired
	PropRepository propRepository;
	
	@Override
	public void addAd(Ad ad) {
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
		if((ad = adRepository.findOne(id))== null){
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
		propRepository.save(prop);
	}

	@Override
	public List<Prop> getProps() {
		return propRepository.findByIsDeleted(false);
	}

	@Override
	public String deleteProp(Long id) {
		Prop prop;
		if((prop = propRepository.findOne(id))==null) {
			return "Id doesn't exist";
		}
		prop.setDeleted(true);
		propRepository.save(prop);
		return "Success";
	}

	@Override
	public Prop getProp(Long id) {
		return propRepository.findOne(id);
	}

	@Override
	public String updateProp(Long id, int amount) {
		Prop prop;
		if((prop = propRepository.findOne(id))==null) {
			return "Id doesn't exist";
		}
		System.out.println("\n\n" + prop.getAmount() + "\n\n");
		if(prop.getAmount() < amount) {
			return "Not enough props";
		}else {
			prop.setAmount(prop.getAmount() - amount);
			propRepository.save(prop);
			return "Success";
		}
	}

	@Override
	public String addAdBid(Long ad_id, Bid bid) {
		Ad ad;
		if((ad = adRepository.findOne(ad_id))== null){
			return "Invalid request";
		}
		bidRepository.save(bid);
		ad.getBids().add(bid);
		System.out.println("\n\n #################" + ad.getBids().toString() + "############## \n\n");
		adRepository.save(ad);
		return "Success";
	}
	

}
