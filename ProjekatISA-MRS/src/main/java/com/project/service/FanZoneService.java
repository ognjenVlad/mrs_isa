package com.project.service;

import java.util.List;

import com.project.domain.Ad;
import com.project.domain.Bid;
import com.project.domain.Prop;
import com.project.utils.Response;

public interface FanZoneService {

	public void addAd(Ad ad,String email);
	public Ad getNextAd();
	public void returnAd(Ad ad);
	public String updateAd(boolean is_published,boolean is_taken,Long id);
	public List<Ad> getPublishedAds();
	public void addProp(Prop prop);
	public List<Prop> getProps();
	public Prop getProp(Long id);
	public Bid getBid(Long id);
	public String deleteProp(Long id);
	public String updateProp(Long id,int amount,String user_email);
	public String addAdBid(Long ad_id,Bid bid,String email);
	public String deleteBid(Long ad_id,Long id);
	public String chooseBid(Long ad_id,Long id);
	public List<String> getUserHistory(String email);
}
