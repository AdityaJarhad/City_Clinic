package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.entity.TestResults;
import com.app.entity.MedicalHistory;

public interface ITestResultsRepository extends JpaRepository<TestResults, Long> {

	List<TestResults> findByMedicalHistory(MedicalHistory medicalHistory);

}
