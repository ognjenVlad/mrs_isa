package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Hall;

public interface HallRepository extends JpaRepository<Hall, Long>{
}
