package com.project.service;

import java.util.List;

import com.project.domain.AdminUser;

public interface AdminService {
	public void addAdmin(AdminUser a);
	public List<AdminUser> getAdmins(String type);
}
