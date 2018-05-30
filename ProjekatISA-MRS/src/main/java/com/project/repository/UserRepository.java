package com.project.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.project.DTO.UserDTO;
import com.project.domain.User;
public interface UserRepository extends JpaRepository<User, Long>{

	User findById(Long id);
	User findByEmail(String email);
	User save(User u);
	

}

