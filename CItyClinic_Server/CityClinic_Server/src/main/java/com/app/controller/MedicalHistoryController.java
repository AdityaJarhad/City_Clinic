package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.dto.MedicalHistoryDTO;
import com.app.entity.MedicalHistory;
import com.app.service.MedicalHistoryService;

@CrossOrigin(origins ="http://localhost:5173")
@RestController
@RequestMapping("/api/medical-history")
public class MedicalHistoryController {

    @Autowired
    private MedicalHistoryService medicalHistoryService;

    @PostMapping
    public ResponseEntity<MedicalHistory> createMedicalHistory(@RequestBody MedicalHistoryDTO medicalHistoryDto) {
        MedicalHistory newMedicalHistory = medicalHistoryService.createMedicalHistory(medicalHistoryDto);
        return ResponseEntity.ok(newMedicalHistory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalHistory> updateMedicalHistory(@PathVariable Long id, @RequestBody MedicalHistoryDTO medicalHistoryDto) {
        MedicalHistory updatedMedicalHistory = medicalHistoryService.updateMedicalHistory(id, medicalHistoryDto);
        return ResponseEntity.ok(updatedMedicalHistory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalHistory> getMedicalHistoryById(@PathVariable Long id) {
        MedicalHistory medicalHistory = medicalHistoryService.getMedicalHistoryById(id);
        return ResponseEntity.ok(medicalHistory);
    }

    @GetMapping
    public ResponseEntity<List<MedicalHistoryDTO>> getAllMedicalHistories() {
        List<MedicalHistoryDTO> medicalHistories = medicalHistoryService.getAllMedicalHistories();
        return ResponseEntity.ok(medicalHistories);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMedicalHistory(@PathVariable Long id) {
        String response = medicalHistoryService.deleteMedicalHistory(id);
        return ResponseEntity.ok(response);
    }
}
