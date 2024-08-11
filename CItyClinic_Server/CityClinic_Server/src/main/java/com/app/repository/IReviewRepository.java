package com.app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.app.entity.Review;
import com.app.entity.Patient;
import com.app.entity.Doctor;

public interface IReviewRepository extends JpaRepository<Review, Long> {

	List<Review> findByPatient(Patient patient);

	List<Review> findByDoctor(Doctor doctor);

}
