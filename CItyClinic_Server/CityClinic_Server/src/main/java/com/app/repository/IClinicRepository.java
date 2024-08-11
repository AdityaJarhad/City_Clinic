package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.entity.Clinic;
import com.app.entity.Location;

public interface IClinicRepository extends JpaRepository<Clinic, Long> {

	Clinic findByName(String name);

	List<Clinic> findByLocation(Location location);

}
