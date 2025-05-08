package com.vica.trips.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vica.trips.models.Stop;
import com.vica.trips.controllers.mappers.DayStopLocationDTO;
import com.vica.trips.controllers.mappers.StopMapper;
import com.vica.trips.controllers.mappers.StopsByDateResponse;
import com.vica.trips.repositories.StopsRepository;

@RestController
@RequestMapping("/api/v1/stops")
public class StopsController {
	
	@Autowired
    private StopsRepository stopsRepo;
	
	@Autowired
    private StopMapper stopMapper;
	
	@GetMapping("/bydate/{date}")
	public StopsByDateResponse filter(@PathVariable("date") String date){
		
		LocalDate localDate = LocalDate.parse(date);
		List<Stop> all = stopsRepo.findAllByDate( localDate); 
		
		List<DayStopLocationDTO> flatList = all.stream()
	        .map(stopMapper::stopToCustomResponse)
	        .collect(Collectors.toList());
		
		StopsByDateResponse resp = new StopsByDateResponse( date, flatList);
		return resp;
	}
	
	@GetMapping("/bylocation/{locationId}")
	public List<java.time.LocalDate> getDates(@PathVariable Integer locationId) {
	    List<java.time.LocalDate> dates = stopsRepo.findDatesByLocationId(locationId);
	    return dates;
	}
	
	@GetMapping("/years")
	public List<Integer> getDistinctYears(){
		List<Integer> years = stopsRepo.findDistinctYears();
		return years;
	}
	
	@GetMapping("/dates/byyear/{yearGiven}")
	public List<java.time.LocalDate> getDatesByYear(@PathVariable Integer yearGiven){
		List<java.time.LocalDate> datesInYear = stopsRepo.findDatesByYear( yearGiven);
		return datesInYear;
	}
}
