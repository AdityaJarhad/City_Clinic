package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.DoctorRegistrationRequest;
import com.app.entity.Doctor;
import com.app.service.DoctorService;

//@CrossOrigin(origins ="http://localhost:5173")
@CrossOrigin(origins = {"http://localhost:5173", "https://cityclinic.vercel.app"})
@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;


    @PostMapping("/register")
    public void registerDoctor(@RequestBody DoctorRegistrationRequest request) {
    	System.out.println(request);
        doctorService.registerDoctor(request);
    }

    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id);
    }

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @PutMapping("/{id}")
    public void updateDoctor(@PathVariable Long id, @RequestBody DoctorRegistrationRequest request) {
    	System.out.println("____________________________________");
    	System.out.println(id+" "+request);
        doctorService.updateDoctor(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
    }
    
    @GetMapping("/user/{userId}") // New endpoint to get DoctorRegistrationRequest by user ID
    public DoctorRegistrationRequest findByUserId(@PathVariable Long userId) {
        return doctorService.findByUserId(userId);
    }
    
    @GetMapping("/registrations")
    public List<DoctorRegistrationRequest> getAllDoctorRegistrations() {
        return doctorService.getAllDoctorRegistrations();
    }

}
