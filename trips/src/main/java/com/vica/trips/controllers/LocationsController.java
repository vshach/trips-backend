package com.vica.trips.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vica.trips.models.Location;
import com.vica.trips.repositories.LocationRepository;

@RestController
@RequestMapping("/api/v1/locations")
public class LocationsController {

	@Autowired
    private LocationRepository locationRepo;
	
	@GetMapping
	public List<Location> list(){
				
		List<Location> listLocations = locationRepo.findAllByOrderByNameAsc();        
        return listLocations;
	}
}
