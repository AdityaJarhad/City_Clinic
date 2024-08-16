package com.app.controller;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AppointmentDTO;
import com.app.dto.BookedAppointmentDTO;
import com.app.dto.DetailedAppointmentDTO;
import com.app.service.AppointmentService;


@CrossOrigin(origins ="http://localhost:5173")
@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	@PostMapping("/post")
	public ResponseEntity<?> newAppointment(@Valid @RequestBody AppointmentDTO dto, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			bindingResult.getFieldErrors().forEach(error -> {
				errors.put(error.getField(), error.getDefaultMessage());
			});
			return ResponseEntity.badRequest().body(errors);
		}

		AppointmentDTO createdAppointment = appointmentService.createAppointment(dto);

		return ResponseEntity.ok(createdAppointment);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateAppointment(@Valid @PathVariable Long id, @RequestBody AppointmentDTO appointmentDto,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			bindingResult.getFieldErrors().forEach(error -> {
				errors.put(error.getField(), error.getDefaultMessage());
			});
			return ResponseEntity.badRequest().body(errors);
		}

		AppointmentDTO updatedAppointment = appointmentService.updateAppointment(id, appointmentDto);
		return ResponseEntity.ok(updatedAppointment);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getAppointmentById(@PathVariable Long id) {

		AppointmentDTO appointment = appointmentService.getAppointmentById(id);
		return ResponseEntity.ok(appointment);
	}

	@GetMapping
	public ResponseEntity<List<AppointmentDTO>> getAllAppointments() {

		List<AppointmentDTO> appointments = appointmentService.getAllAppointments();
		return ResponseEntity.ok(appointments);
	}
	
	 @GetMapping("/doctor/{doctorId}")
	    public List<DetailedAppointmentDTO> getAppointmentsByDoctorId(@PathVariable Long doctorId) {
	        return appointmentService.getAppointmentsByDoctorId(doctorId);
	    }
	 
	 @GetMapping("/booked/{patientId}")
	    public ResponseEntity<List<BookedAppointmentDTO>> getBookedAppointmentsByPatientId(@PathVariable Long patientId) {
	        List<BookedAppointmentDTO> bookedAppointments = appointmentService.getBookedAppointmentsByPatientId(patientId);
	        return ResponseEntity.ok(bookedAppointments);
	    }
	 
	 
	 @GetMapping("/doctor/{doctorId}/today")
	    public ResponseEntity<List<DetailedAppointmentDTO>> getAppointmentsByDoctorIdForToday(@PathVariable Long doctorId) {
	        LocalDate today = LocalDate.now();
	        List<DetailedAppointmentDTO> appointments = appointmentService.getAppointmentsByDoctorIdAndDate(doctorId, today);
	        return ResponseEntity.ok(appointments);
	    }

	    @GetMapping("/doctor/{doctorId}/week")
	    public ResponseEntity<List<DetailedAppointmentDTO>> getAppointmentsByDoctorIdForThisWeek(@PathVariable Long doctorId) {
	        LocalDate today = LocalDate.now();
	        LocalDate startOfWeek = today.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
	        LocalDate endOfWeek = today.with(TemporalAdjusters.nextOrSame(java.time.DayOfWeek.SUNDAY));
	        List<DetailedAppointmentDTO> appointments = appointmentService.getAppointmentsByDoctorIdAndDateRange(doctorId, startOfWeek, endOfWeek);
	        return ResponseEntity.ok(appointments);
	    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAppointment(@PathVariable Long id){
		
		appointmentService.deleteAppointment(id);
		return ResponseEntity.noContent().build();
	}

}
