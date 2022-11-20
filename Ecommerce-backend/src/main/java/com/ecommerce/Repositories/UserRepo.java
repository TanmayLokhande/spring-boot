package com.ecommerce.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.Entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	Optional<User> findByUserEmail(String email);
	
}
