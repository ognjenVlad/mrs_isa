package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Prop;

public interface PropRepository extends JpaRepository<Prop, Long>{
	List<Prop> findByIsDeleted(boolean isDeleted);
}
