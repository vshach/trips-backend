package com.vica.trips.services;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vica.trips.models.Photo;
import com.vica.trips.repositories.PhotosRepository;

@Service
public class FilesAndMetaDataService {

	private final FileService fileService;
	private final PhotosMetaDataService metaDataService;
	private final PhotosRepository photosRepository;
	
	public FilesAndMetaDataService( FileService fileService,
				       				PhotosMetaDataService metaDataService,
				       				PhotosRepository photosRepository ) {
		this.fileService = fileService;
		this.metaDataService = metaDataService;
		this.photosRepository = photosRepository;
	}
	
	public void uploadNewPhoto(MultipartFile file, String locationID, String people, String dateString) throws IOException {
		try {
			fileService.newFileUpload(  file,  locationID,  people, dateString);
			metaDataService.saveNewPhoto(dateString, people, locationID, file.getOriginalFilename());
		}catch (IOException e) {
                // File operation failed — log it, rethrow, or handle
                throw e; //new RuntimeException("Failed to move file. DB update aborted.", e);
            }
	}
	
	public void updateExistingPhoto(int photoId, int locationID, String people, String dateString) throws IOException {
		//is different date?
		//if yes - fileService.moveFile		
		Photo existingPhoto = photosRepository.findById( photoId )
                .orElseThrow(() -> new RuntimeException("Photo not found"));

    	LocalDate dateTakenDate = existingPhoto.getDateTaken();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
        String formattedDateTaken = dateTakenDate.format( formatter);
        if ( ! dateString.equals( formattedDateTaken ) ) {
        	fileService.moveFile(existingPhoto.getFilepath(), existingPhoto.getDateTaken(), dateString);
        }
		metaDataService.updatePhotoData( existingPhoto, locationID, people, dateString);
	}
}
