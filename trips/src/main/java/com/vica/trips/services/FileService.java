package com.vica.trips.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vica.trips.repositories.PhotosRepository;

@Service
public class FileService {
	
	private final DatePathUtil datePathUtil;

    public FileService(DatePathUtil datePathUtil) {
        this.datePathUtil = datePathUtil;
    }
	
	public void newFileUpload( MultipartFile file, String locationID, String people, String dateString) throws IOException{
		
		System.out.println("Working dir: " + System.getProperty("user.dir"));     	
    	
    	//transform the date into full path 
    	String fullPath = datePathUtil.convertToFilepath( dateString );
    	    	
        // Make sure the directorIES along the path exist
    	Files.createDirectories(Paths.get(fullPath).toAbsolutePath().normalize());            

        // Create unique filename
        String filename = file.getOriginalFilename();
        Path filepath = Paths.get(fullPath, filename);

        // Save file to disk
        Files.write(filepath, file.getBytes());
        
	}
	
	//when a date is updated, the file needs to move to the new date based directory
	public void moveFile(String fileName, LocalDate oldDate, String newDateString ) throws IOException {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedOldDate = oldDate.format( formatter);
        String locationOrigin = datePathUtil.convertToFilepath(formattedOldDate);        
		Path sourcePath = Paths.get(locationOrigin+"/"+fileName);
		
		String locationDestination = datePathUtil.convertToFilepath( newDateString );
	    Path targetPath = Paths.get(locationDestination+"/"+fileName);

	    // Ensure destination directory exists
	    Files.createDirectories(targetPath.getParent());

	    // Move the file (overwriting if it already exists at the destination)
	    Files.move(sourcePath, targetPath);
	}
}
