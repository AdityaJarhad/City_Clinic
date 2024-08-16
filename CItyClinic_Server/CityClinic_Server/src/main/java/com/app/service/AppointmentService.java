package com.app.service;

import java.util.List;

import com.app.dto.AppointmentDTO;
import com.app.dto.DetailedAppointmentDTO;

public interface AppointmentService {
	
	AppointmentDTO createAppointment(AppointmentDTO appointmentDto);

	AppointmentDTO updateAppointment(Long appointmentId, AppointmentDTO appointmentDto);

	AppointmentDTO getAppointmentById(Long appointmentId);

	List<com.app.dto.AppointmentDTO> getAllAppointments();

	void deleteAppointment(Long appointmentId);
	
	 public List<DetailedAppointmentDTO> getAppointmentsByDoctorId(Long doctorId);
}
