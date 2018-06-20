package com.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.domain.Ad;
import com.project.domain.Bid;
import com.project.domain.Prop;
import com.project.domain.User;
import com.project.repository.AdRepository;
import com.project.repository.BidRepository;
import com.project.repository.PropRepository;
import com.project.repository.UserRepository;

@Service
public class FanZoneImpl implements FanZoneService {
	@Autowired
	AdRepository adRepository;

	@Autowired
	BidRepository bidRepository;

	@Autowired
	PropRepository propRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void addAd(Ad ad,String email) {
		ad.setUser(userRepository.findByEmail(email));
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
		ArrayList<Ad> ads = (ArrayList<Ad>) adRepository.findByIsPublished(true);
/*		for (Ad ad : ads) {
			for (int i = 0 ; i < ad.getBids().size();i++) {
				if(!ad.getBids().get(i).isDeleted())
					ad.getBids().remove(i);
			}
		}*/
		return ads;
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
	public String updateProp(Long id, int amount,String user_email) {
		Prop prop;
		if((prop = propRepository.findOne(id))==null) {
			return "Id doesn't exist";
		}
		System.out.println("\n\n" + prop.getAmount() + "\n\n");
		if(prop.getAmount() < amount) {
			return "Not enough props";
		}else {
			prop.setAmount(prop.getAmount() - amount);
			User user = userRepository.findByEmail(user_email);
			user.getHistory().add("You bought " + Integer.toString(amount) + " items related to post <AHF593SLE" + Long.toString(id)+ ">. You can pick it up at .."); 
			propRepository.save(prop);
			userRepository.save(user);
			return "Success";
		}
	}

	@Override
	public String addAdBid(Long ad_id, Bid bid,String email) {
		System.out.println(email + " EMAILEMAILEMAILEMAILEMAILEMAILEMAIL");
		bid.setUser(userRepository.findByEmail(email));
		Long bid_id = bid.getId();
		Ad ad;
		if((ad = adRepository.findOne(ad_id))== null){
			return "Invalid request";
		}
		
		bidRepository.save(bid);
		
		System.out.println(bid);
		if(bid_id == null) {
			ad.getBids().add(bid);
		}else {
			for(int i = 0 ; i < ad.getBids().size();i++) {
				if(ad.getBids().get(i).getId().equals(bid_id)) {
					ad.getBids().get(i).setValue(bid.getValue());
				}
			}
		}

		System.out.println("\n\n #################" + ad.getBids().toString() + "############## \n\n");
		adRepository.save(ad);
		return "Success";
	}

	@Override
	public Bid getBid(Long id) {
		return bidRepository.findOne(id);
	}
	
	@Override
	public String deleteBid(Long ad_id,Long id) {
		Bid bid;
		Ad ad;
		if((ad = adRepository.findOne(ad_id))==null) {
			return "Id doesn't exist";
		}
		if((bid = bidRepository.findOne(id)) == null) {
			return "Id doesn't exist";
		}
		
		int index = 0;
		for(int i = 0 ; i < ad.getBids().size();i++) {
			if(ad.getBids().get(i).getId() == bid.getId()) {
				index = i;
				break;
			}
		}
		ad.getBids().remove(index);
		adRepository.save(ad);
		return "Success";
	}

	@Override
	public String chooseBid(Long ad_id, Long id) {
		Ad ad = adRepository.findOne(ad_id);
		if(ad == null) {
			return "Ad doesn't exist anymore";
		}
		if(ad.isPublished() == false) {
			return "Ad doesn't exist anymore";
		}
		User user;
		for(Bid bid : ad.getBids()) {
			user = userRepository.findByEmail(bid.getUser().getEmail());
			if(user == null) {
				System.out.println("User wasn't found , error.");
				continue;
			}
			if(user.getHistory() == null) {
				user.setHistory(new ArrayList<String>());
			}
			System.out.println("BEFORE " + user.getHistory().size());
			if(bid.getId() == id) {
				user.getHistory().add("Your bid has been accepted on ad : " + ad.getTitle() +".");
			}else {
				user.getHistory().add("Your bid has been rejected on ad : " + ad.getTitle() +".");
			}
			userRepository.save(user);
			System.out.println("AFTER " +user.getHistory().size());
		}
		
		ad.setPublished(false);
		ad.setTaken(true);
		
		adRepository.save(ad);

		return "Success";
	}

	@Override
	public List<String> getUserHistory(String email) {
		User user = userRepository.findByEmail(email);
		
		if(user == null) {
			return null;
		}else {
			return user.getHistory();
		}
	}

}
