package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.MedicalHistoryDTO;
import com.app.entity.MedicalHistory;
import com.app.repository.IMedicalHistoryRepository;

@Service
@Transactional
public class MedicalHistoryServiceImpl implements MedicalHistoryService {

    @Autowired
    private IMedicalHistoryRepository medicalHistoryRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public MedicalHistory createMedicalHistory(MedicalHistoryDTO medicalHistoryDto) {
        MedicalHistory medicalHistory = mapper.map(medicalHistoryDto, MedicalHistory.class);
        return medicalHistoryRepo.save(medicalHistory);
    }

    @Override
    public MedicalHistory updateMedicalHistory(Long medicalHistoryId, MedicalHistoryDTO medicalHistoryDto) {
        MedicalHistory medicalHistory = medicalHistoryRepo.findById(medicalHistoryId).orElseThrow();
        mapper.map(medicalHistoryDto, medicalHistory);
        return medicalHistoryRepo.save(medicalHistory);
    }

    @Override
    public MedicalHistory getMedicalHistoryById(Long medicalHistoryId) {
        return medicalHistoryRepo.findById(medicalHistoryId).orElseThrow();
    }

    @Override
    public List<MedicalHistoryDTO> getAllMedicalHistories() {
        return medicalHistoryRepo.findAll()
                .stream()
                .map(medicalHistory -> mapper.map(medicalHistory, MedicalHistoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public String deleteMedicalHistory(Long medicalHistoryId) {
        MedicalHistory medicalHistory = medicalHistoryRepo.findById(medicalHistoryId).orElseThrow();
        medicalHistoryRepo.delete(medicalHistory);
        return "Medical history deleted: " + medicalHistory.getId();
    }
}
