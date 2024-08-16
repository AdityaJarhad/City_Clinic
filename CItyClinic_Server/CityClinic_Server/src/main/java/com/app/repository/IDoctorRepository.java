package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Clinic;
import com.app.entity.Doctor;
import com.app.entity.Specialization;
import com.app.entity.User;

public interface IDoctorRepository extends JpaRepository<Doctor, Long> {

	List<Doctor> findBySpecialization(Specialization specialization);

	List<Doctor> findByClinic(Clinic clinic);
	
	Optional<Doctor> findByUserId(Long userId); 
	
	Optional<Doctor> findByUser(User user);

}
