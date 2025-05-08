package com.vica.trips.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vica.trips.models.Photo;
import com.vica.trips.repositories.PhotosRepository;

@Service
public class PhotosMetaDataService {

    @Autowired
    private PhotosRepository photosRepository;
    
    public Set<String> getPersonNames(){
		List<String> data = photosRepository.loadPeopleNames();		
		Set<String> result = data.stream() //Arrays.stream(data)
				.flatMap( s -> Arrays.stream( s.split(",") ))
				.collect( Collectors.toCollection( TreeSet::new)); //TreeSet does distinct and sorts
		return result;
	}
    
    public Photo updatePhotoData( Photo existingPhoto, int locationId, String people, String dateString) {
    	
    	existingPhoto.setLocationID(locationId);
    	existingPhoto.setPeople(people);
    	
    	LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
    	existingPhoto.setDateTaken( date );
    	
    	return photosRepository.save(existingPhoto);
    }    
    
    public int saveNewPhoto(String dateString, String people, String locationID, String fileName){
    	Photo photoData = new Photo();
	    LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
	    photoData.setDateTaken( date);
	    photoData.setPeople( people );
	    int locIdInt = Integer.parseInt(locationID);
	    photoData.setLocationID( locIdInt );
	    photoData.setFilepath( fileName );
	    
	    Photo newPhoto = photosRepository.save( photoData );
        return newPhoto.getId();
	}
}