package com.project.service;

import java.util.List;

import com.project.domain.Ad;
import com.project.domain.Bid;
import com.project.domain.Prop;

public interface FanZoneService {

	public void addAd(Ad ad);
	public Ad getNextAd();
	public void returnAd(Ad ad);
	public String updateAd(boolean is_published,boolean is_taken,Long id);
	public List<Ad> getPublishedAds();
	public void addProp(Prop prop);
	public List<Prop> getProps();
	public Prop getProp(Long id);
	public String deleteProp(Long id);
	public String updateProp(Long id,int amount);
	public String addAdBid(Long ad_id,Bid bid);
}
