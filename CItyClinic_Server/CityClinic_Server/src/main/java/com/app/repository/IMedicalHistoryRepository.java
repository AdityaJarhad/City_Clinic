package com.app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.app.entity.MedicalHistory;
import com.app.entity.Patient;
import com.app.entity.Doctor;

public interface IMedicalHistoryRepository extends JpaRepository<MedicalHistory, Long> {

	List<MedicalHistory> findByPatient(Patient patient);

	List<MedicalHistory> findByDoctor(Doctor doctor);

}
