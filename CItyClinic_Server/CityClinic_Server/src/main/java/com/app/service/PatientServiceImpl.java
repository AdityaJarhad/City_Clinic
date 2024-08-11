package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.PatientDTO;
import com.app.entity.Patient;
import com.app.entity.User;
import com.app.repository.IPatientRepository;
import com.app.repository.IUserRepository;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private IPatientRepository patientRepo;

    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PatientDTO createPatient(Long userId) {
        User user = userRepo.findById(userId)
                            .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the user is a patient
        if (!user.getRole().equals("Patient")) {
            throw new RuntimeException("User is not a Patient");
        }

        Patient patient = new Patient();
        patient.setUser(user);

        Patient savedPatient = patientRepo.save(patient);
        return modelMapper.map(savedPatient, PatientDTO.class);
    }

    @Override
    public PatientDTO getPatientById(Long patientId) {
        Patient patient = patientRepo.findById(patientId)
                                     .orElseThrow(() -> new RuntimeException("Patient not found"));
        return modelMapper.map(patient, PatientDTO.class);
    }

    @Override
    public void deletePatient(Long patientId) {
        if (!patientRepo.existsById(patientId)) {
            throw new RuntimeException("Patient not found");
        }
        patientRepo.deleteById(patientId);
    }
    
    @Override
    public List<PatientDTO> getAllPatients() {
        List<Patient> patients = patientRepo.findAll();
        return patients.stream()
                       .map(patient -> modelMapper.map(patient, PatientDTO.class))
                       .collect(Collectors.toList());
    }
}
