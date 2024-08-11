package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.entity.Patient;
import com.app.entity.User;

public interface IPatientRepository extends JpaRepository<Patient, Long> {

	Patient findByUser(User user);

}
