package com.app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.app.entity.Appointment;
import com.app.entity.Patient;
import com.app.entity.Doctor;

public interface IAppointmentRepository extends JpaRepository<Appointment, Long> {

	List<Appointment> findByPatient(Patient patient);

	List<Appointment> findByDoctor(Doctor doctor);

}
