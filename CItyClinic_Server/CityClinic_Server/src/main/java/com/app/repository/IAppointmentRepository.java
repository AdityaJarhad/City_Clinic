package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entity.Appointment;
import com.app.entity.Doctor;
import com.app.entity.Patient;

public interface IAppointmentRepository extends JpaRepository<Appointment, Long> {

	List<Appointment> findByPatient(Patient patient);

	List<Appointment> findByDoctor(Doctor doctor);
	
	
	//@Query(value = "SELECT * FROM appointments WHERE doctor_id = :doctorId", nativeQuery = true) //specifies the native SQL query.

	@Query("SELECT a FROM Appointment a WHERE a.doctor.id = :doctorId")//JPQL
    List<Appointment> findAppointmentsByDoctorId(@Param("doctorId") Long doctorId);

}
