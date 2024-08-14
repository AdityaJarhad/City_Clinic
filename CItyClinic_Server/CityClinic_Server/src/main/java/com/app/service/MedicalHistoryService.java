package com.app.service;

import java.util.List;

import com.app.dto.MedicalHistoryDTO;
import com.app.entity.MedicalHistory;

public interface MedicalHistoryService {
	MedicalHistory createMedicalHistory(MedicalHistoryDTO medicalHistoryDto);

	MedicalHistory updateMedicalHistory(Long medicalHistoryId, MedicalHistoryDTO medicalHistoryDto);

	MedicalHistory getMedicalHistoryById(Long medicalHistoryId);

	List<MedicalHistoryDTO> getAllMedicalHistories();

	String deleteMedicalHistory(Long medicalHistoryId);
}
