package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.app.entity.User;

public interface IUserRepository extends JpaRepository<User, Long> {

	
		//derived query method
	Optional<User> findByEmail(String email);
	
		//derived query metho
		boolean existsByEmail(String email);

}
