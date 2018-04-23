package repository;


import org.springframework.data.repository.Repository;

import DTOs.UserDTO;
import domain.User;
public interface UserRepository extends Repository<User, Long>{

	UserDTO findByEmail(String email);
}

