package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.domain.Invited;

public interface InvitedRepository extends JpaRepository<Invited, Long>{
	Invited save(Invited r);
}