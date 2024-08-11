package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.entity.User;

public interface IUserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

}
