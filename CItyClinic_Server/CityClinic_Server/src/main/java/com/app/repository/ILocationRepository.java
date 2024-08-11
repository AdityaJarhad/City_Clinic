package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.entity.Location;

public interface ILocationRepository extends JpaRepository<Location, Long> {

	List<Location> findByCity(String city);

}
