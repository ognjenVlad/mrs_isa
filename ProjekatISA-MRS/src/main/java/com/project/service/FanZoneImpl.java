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
import com.project.utils.Response;

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
		
		//bid.setDeleted(true);
		bidRepository.save(bid);
		for(int i = 0 ; i < ad.getBids().size();i++) {
			if(ad.getBids().get(i).getId().equals(bid.getId())) {
				ad.getBids().remove(i);
				continue;
			}
		}
		adRepository.save(ad);
		return "Success";
	}

}
