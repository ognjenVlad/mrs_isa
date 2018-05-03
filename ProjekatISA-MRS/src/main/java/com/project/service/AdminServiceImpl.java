package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.domain.AdminUser;
import com.project.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminRepository aRepository;
	
	@Override
	public void addAdmin(AdminUser a) {
		if(aRepository.findByUsername(a.getUsername())== null) {
			aRepository.save(a);
		}
	}

	@Override
	public List<AdminUser> getAdmins(String type) {
		return aRepository.findByAdminType(type);
	}
}
