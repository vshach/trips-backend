package com.vica.trips.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vica.trips.models.Location;

public interface  LocationRepository extends JpaRepository<Location, Integer>{

	List<Location> findAllByOrderByNameAsc();
}
