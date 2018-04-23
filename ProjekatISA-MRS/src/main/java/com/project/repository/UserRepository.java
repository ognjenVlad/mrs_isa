package com.project.repository;


import org.springframework.data.repository.Repository;

import com.project.DTO.UserDTO;
import com.project.domain.User;
public interface UserRepository extends Repository<User, Long>{

	UserDTO findByEmail(String email);
}

