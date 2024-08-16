package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entity.Patient;
import com.app.entity.User;

public interface IPatientRepository extends JpaRepository<Patient, Long> {

	Patient findByUser(User user);
	
	@Query("SELECT p FROM Patient p WHERE p.user.id = :userId")
	Optional<Patient> findByUserId(@Param("userId") Long userId);

}
