package com.app.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.MedicalHistoryDTO;
import com.app.entity.MedicalHistory;
import com.app.entity.Patient;
import com.app.entity.User;
import com.app.repository.IDoctorRepository;
import com.app.repository.IMedicalHistoryRepository;
import com.app.repository.IPatientRepository;
import com.app.repository.IUserRepository;

@Service
@Transactional
public class MedicalHistoryServiceImpl implements MedicalHistoryService {

	 @Autowired
	    private IMedicalHistoryRepository medicalHistoryRepository;

	    @Autowired
	    private IPatientRepository patientRepository;

	    @Autowired
	    private IDoctorRepository doctorRepository;
	    
	    @Autowired
	    private IUserRepository UserRepository;
	    
	    @Autowired
		private ModelMapper modelMapper;

	    @Override
	    public MedicalHistory createMedicalHistory(MedicalHistoryDTO dto) {

	        MedicalHistory medicalHistory = new MedicalHistory();
	        medicalHistory.setPatient(patientRepository.findById(dto.getPatientId()).orElseThrow(() -> new RuntimeException("Patient not found")));
	        medicalHistory.setDoctor(doctorRepository.findByUserId(dto.getDoctorId()).orElseThrow(() -> new RuntimeException("Doctor not found")));
	        medicalHistory.setDiagnosis(dto.getDiagnosis());
	        medicalHistory.setTreatment(dto.getTreatment());
	        medicalHistory.setVisitDate(dto.getVisitDate());
	        medicalHistory.setDocumentURL(dto.getDocumentURL());
	        System.out.println(medicalHistory+"_________________");
	        return medicalHistoryRepository.save(medicalHistory);
	    }

	    @Override
	    public MedicalHistory updateMedicalHistory(Long id, MedicalHistoryDTO dto) {
	    	
	    	Patient patient = patientRepository.findById(id)
		            .orElseThrow(() -> new RuntimeException("Patient not found"));
	    	
	        MedicalHistory medicalHistory = medicalHistoryRepository.findFirstByPatient(patient).get();
	        medicalHistory.setDiagnosis(dto.getDiagnosis());
	        medicalHistory.setTreatment(dto.getTreatment());
	        medicalHistory.setVisitDate(dto.getVisitDate());
	        medicalHistory.setDocumentURL(dto.getDocumentURL());
	        return medicalHistoryRepository.save(medicalHistory);
	    }

	    @Override
	    public MedicalHistory getMedicalHistory(Long id) {
	    	
	    	
	        Patient patient = patientRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Patient not found"));
	        
	       
	        return medicalHistoryRepository.findFirstByPatient(patient).get();
	    	
	    }
	    
	    @Override
	    public MedicalHistoryDTO getMedicalHistoryByPatient(Long UserId) {
	    	
	    	@SuppressWarnings("deprecation")
			User user = UserRepository.getById(UserId);
	    	
	        Patient patient = patientRepository.findByUserId(user.getId()).get();
	        
	        MedicalHistoryDTO dto= modelMapper.map(medicalHistoryRepository.findFirstByPatient(patient).get(), MedicalHistoryDTO.class);
	        
	        return dto;
	    }

	    @Override
	    public void deleteMedicalHistory(Long id) {
	        if (!medicalHistoryRepository.existsById(id)) {
	            throw new RuntimeException("Medical History not found");
	        }
	        medicalHistoryRepository.deleteById(id);
	    }
}
