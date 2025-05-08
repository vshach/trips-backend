package com.vica.trips.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vica.trips.models.Stop;

public interface StopsRepository  extends JpaRepository<Stop, Integer> {

	List<Stop> findAllByDate(LocalDate date);
	
	// Query by the ID of the location
    //List<Stop> findByLocId(Integer locationId);
    
    @Query("SELECT DISTINCT s.date FROM Stop s WHERE s.location.id = :locId")
    List<java.time.LocalDate> findDatesByLocationId(Integer locId);
    
    @Query("SELECT DISTINCT YEAR(s.date) FROM Stop s ORDER BY YEAR(s.date)")
    List<Integer> findDistinctYears(); 
    
    @Query("select DISTINCT s.date FROM Stop s  WHERE YEAR(s.date) = :yearGiven")
    List<java.time.LocalDate> findDatesByYear(int yearGiven);
    
    //@Query(value = "SELECT s.date FROM stops s WHERE YEAR(s.date) = :year", nativeQuery = true)
    //List<java.sql.Date> findDatesByYear(@Param("year") int year);
}
