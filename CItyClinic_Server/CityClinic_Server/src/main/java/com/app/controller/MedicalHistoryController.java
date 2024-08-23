package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.MedicalHistoryDTO;
import com.app.entity.MedicalHistory;
import com.app.service.MedicalHistoryService;

@RestController
@RequestMapping("/api/medical-history")
//@CrossOrigin(origins ="http://localhost:5173")
@CrossOrigin(origins = {"http://localhost:5173", "https://cityclinic.vercel.app"})
public class MedicalHistoryController {

    @Autowired
    private MedicalHistoryService medicalHistoryService;

    @PostMapping
    public ResponseEntity<MedicalHistory> createMedicalHistory(@RequestBody MedicalHistoryDTO dto) {
        MedicalHistory medicalHistory = medicalHistoryService.createMedicalHistory(dto);
        return new ResponseEntity<>(medicalHistory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalHistory> updateMedicalHistory(
            @PathVariable Long id, @RequestBody MedicalHistoryDTO dto) {
        MedicalHistory medicalHistory = medicalHistoryService.updateMedicalHistory(id, dto);
        return new ResponseEntity<>(medicalHistory, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalHistory> getMedicalHistory(@PathVariable Long id) {
        MedicalHistory medicalHistory = medicalHistoryService.getMedicalHistory(id);
        return new ResponseEntity<>(medicalHistory, HttpStatus.OK);
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<MedicalHistoryDTO> getMedicalHistoryByPatient(@PathVariable Long patientId) {
    	MedicalHistoryDTO medicalHistories = medicalHistoryService.getMedicalHistoryByPatient(patientId);
        return new ResponseEntity<>(medicalHistories, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicalHistory(@PathVariable Long id) {
        medicalHistoryService.deleteMedicalHistory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
