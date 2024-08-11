package com.app.service;

import java.util.List;

import com.app.dto.PatientDTO;

public interface PatientService {
    PatientDTO createPatient(Long userId);
    PatientDTO getPatientById(Long patientId);
    void deletePatient(Long patientId);
    List<PatientDTO> getAllPatients();
}
