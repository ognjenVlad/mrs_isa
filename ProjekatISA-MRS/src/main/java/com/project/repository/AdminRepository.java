package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.AdminUser;

public interface AdminRepository  extends JpaRepository<AdminUser, Long>{
	AdminUser findByUsername(String username);
	List<AdminUser> findByAdminType(String adminType);
}
