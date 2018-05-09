package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Ad;

public interface AdRepository extends JpaRepository<Ad, Long>{

}
