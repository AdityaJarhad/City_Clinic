package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.entity.Specialization;

public interface ISpecializationRepository extends JpaRepository<Specialization, Long> {

}
