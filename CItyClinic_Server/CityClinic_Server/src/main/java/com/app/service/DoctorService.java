package com.app.service;

import java.util.List;

import com.app.dto.DoctorRegistrationRequest;
import com.app.entity.Doctor;

public interface DoctorService {
	void registerDoctor(DoctorRegistrationRequest request);

	Doctor getDoctorById(Long id);

	List<Doctor> getAllDoctors();

	void updateDoctor(Long id, DoctorRegistrationRequest request);

	void deleteDoctor(Long id);
}
