package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Doctor;
import com.app.entity.MedicalHistory;
import com.app.entity.Patient;

public interface IMedicalHistoryRepository extends JpaRepository<MedicalHistory, Long> {

	List<MedicalHistory> findByPatient(Patient patient);

	List<MedicalHistory> findByDoctor(Doctor doctor);
	
	Optional<MedicalHistory> findFirstByPatient(Patient patient);

}
