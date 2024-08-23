package com.app.service;

import java.time.LocalDate;
import java.util.List;

import com.app.dto.AppointmentDTO;
import com.app.dto.BookedAppointmentDTO;
import com.app.dto.DetailedAppointmentDTO;
import com.app.dto.PatientInfo;

public interface AppointmentService {
	
	AppointmentDTO createAppointment(AppointmentDTO appointmentDto);

	AppointmentDTO updateAppointment(Long appointmentId, AppointmentDTO appointmentDto);

	AppointmentDTO getAppointmentById(Long appointmentId);

	List<com.app.dto.AppointmentDTO> getAllAppointments();

	void deleteAppointment(Long appointmentId);
	
	 public List<DetailedAppointmentDTO> getAppointmentsByDoctorId(Long doctorId);
	 
	 List<BookedAppointmentDTO> getBookedAppointmentsByPatientId(Long patientId);
	 
	 public List<DetailedAppointmentDTO> getAppointmentsByDoctorIdAndDate(Long doctorId, LocalDate date);
	 
	 public List<DetailedAppointmentDTO> getAppointmentsByDoctorIdAndDateRange(Long doctorId, LocalDate startDate, LocalDate endDate);
	 
	 public PatientInfo getPatientInfoByAppointmentId(Long appointmentId, Long doctorId);
	 
	 public List<PatientInfo> getPatientsByDoctorId(Long doctorId);
}
