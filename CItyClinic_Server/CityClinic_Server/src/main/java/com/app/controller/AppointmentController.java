package com.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AppointmentDTO;
import com.app.service.AppointmentService;

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
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAppointment(@PathVariable Long id){
		
		appointmentService.deleteAppointment(id);
		return ResponseEntity.noContent().build();
	}

}
