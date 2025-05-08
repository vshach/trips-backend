package com.vica.trips.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vica.trips.models.Photo;

public interface PhotosRepository  extends JpaRepository<Photo, Integer>{

	List<Photo> findAllBylocationID(int locationID);
	
	List<Photo> findAllBylocationIDAndDateTakenGreaterThanEqual(int locationID, LocalDate date);
	
	List<Photo> findAllBylocationIDAndDateTaken(int locationID, LocalDate date);
	
	@Query("select DISTINCT p.people FROM Photo p")
	List<String> loadPeopleNames();
}
