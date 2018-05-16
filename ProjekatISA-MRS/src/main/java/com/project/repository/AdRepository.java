package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Ad;

public interface AdRepository extends JpaRepository<Ad, Long>{
	List<Ad> findByIsPublished(boolean is_published);
}
