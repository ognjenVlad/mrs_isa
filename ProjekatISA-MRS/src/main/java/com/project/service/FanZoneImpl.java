package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.domain.Ad;
import com.project.repository.AdRepository;

@Service
public class FanZoneImpl implements FanZoneService {
	@Autowired
	AdRepository adRepository;
	
	@Override
	public void addAd(Ad ad) {
		adRepository.save(ad);
	}

}
