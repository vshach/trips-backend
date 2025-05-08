package com.vica.trips.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vica.trips.models.Photo;
import com.vica.trips.repositories.PhotosRepository;
import com.vica.trips.services.DatePathUtil;
import com.vica.trips.services.FilesAndMetaDataService;
import com.vica.trips.services.PhotosMetaDataService;

@RestController
@RequestMapping("/api/v1/photos")
public class PhotosMetaDataController {

	@Autowired
    private PhotosMetaDataService photosMetaDataService;

	@Autowired
	private FilesAndMetaDataService filesAndMetaDataService;
	
	@Autowired
    private PhotosRepository photosRepo;
	
    //@Value("${fileDirectory}")
    //private String fileDirectory;

    @Value("${serverPhotosURLpart}")
	private String serverPhotosURLpart;
    
	@GetMapping("/{locID}/{date}")
	public List<Photo> filter(@PathVariable("locID") int locationID, @PathVariable("date") String date){
		//List<Photo> allAtLocation = photosRepo.findAllBylocationID(locationID);
		LocalDate localDate = LocalDate.parse(date);
		List<Photo> all = photosRepo.findAllBylocationIDAndDateTakenGreaterThanEqual(locationID, localDate);
		
		String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString()+"/"+serverPhotosURLpart+"/";

		//transform the date into full path		
		all.stream().
			forEach(photo -> photo.setFilepath( baseUrl + DatePathUtil.convertToFilepath(photo.getDateTaken() ) + photo.getFilepath() ) );		     	
    	return all;
	}
	
	@GetMapping("/exact/{locID}/{date}")
	public List<Photo> filterExactDate(@PathVariable("locID") int locationID, @PathVariable("date") String date){
	
		LocalDate localDate = LocalDate.parse(date);
		List<Photo> all = photosRepo.findAllBylocationIDAndDateTaken(locationID, localDate);
		String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString()+"/"+serverPhotosURLpart+"/";
		//transform the date into full path
		all.stream().
			forEach(photo -> photo.setFilepath( baseUrl + DatePathUtil.convertToFilepath(photo.getDateTaken() ) + photo.getFilepath() ) );		     	
    	
		return all;
	}
	
	@GetMapping("/name/list")
	public Set<String> getPersonNames(){		
		return photosMetaDataService.getPersonNames();
	}
	
	@PutMapping("/update/{id}")
    public ResponseEntity<Map<String, String>> updatePhoto(
	            @PathVariable int id,	            	            
	            @RequestParam("locationId") int locationId,
	            @RequestParam String people,
	            @RequestParam String date) {
	        try {
				filesAndMetaDataService.updateExistingPhoto(id, locationId, people, date);
				return ResponseEntity.ok( Map.of("message", "Moved photo id: " + id) );
	        } catch (IOException e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body( Map.of("error", "Failed to move file") );
	        }
    }

}
