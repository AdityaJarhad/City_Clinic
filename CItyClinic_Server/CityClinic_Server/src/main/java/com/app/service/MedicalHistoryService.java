package com.app.service;

import com.app.dto.MedicalHistoryDTO;
import com.app.entity.MedicalHistory;

public interface MedicalHistoryService {
	
	 public MedicalHistory createMedicalHistory(MedicalHistoryDTO dto);
	 public MedicalHistory updateMedicalHistory(Long id, MedicalHistoryDTO dto);
	 public MedicalHistory getMedicalHistory(Long id);
	 public void deleteMedicalHistory(Long id);
	 public MedicalHistoryDTO getMedicalHistoryByPatient(Long patientId);
}
