package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Bid;

public interface BidRepository extends JpaRepository<Bid, Long>{

}
