package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.AppointmentDTO;
import com.app.entity.Appointment;
import com.app.repository.IAppointmentRepository;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private IAppointmentRepository appointmentRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public AppointmentDTO createAppointment(AppointmentDTO appointmentDto) {
		Appointment appointment = mapper.map(appointmentDto, Appointment.class);
		appointment = appointmentRepo.save(appointment);

		return mapper.map(appointment, AppointmentDTO.class);
	}

	@Override
	public AppointmentDTO updateAppointment(Long appointmentId, AppointmentDTO appointmentDto) {
		Appointment appointment = appointmentRepo.findById(appointmentId)
				.orElseThrow(() -> new RuntimeException("Appointment not found with id : "+appointmentId));
		
//		appointment.setPatient(appointmentDto.getPatientId());
		return null;
	}

	@Override
	public AppointmentDTO getAppointmentById(Long appointmentId) {
		Appointment appointment = appointmentRepo.findById(appointmentId)
				.orElseThrow(() -> new RuntimeException("Appointment not found with id : "+appointmentId));
		
		return mapper.map(appointment, AppointmentDTO.class);
	}

	@Override
	public List<AppointmentDTO> getAllAppointments() {
		List<Appointment> appointments = appointmentRepo.findAll();
		
		return appointments
                .stream()
                .map(appointment -> mapper.map(appointment, AppointmentDTO.class))
                .collect(Collectors.toList());
	}

	@Override
	public void deleteAppointment(Long appointmentId) {
		Appointment appointment = appointmentRepo.findById(appointmentId)
				.orElseThrow(() -> new RuntimeException("Appointment not found with id : "+appointmentId));
		appointmentRepo.delete(appointment);
		
	}

}
