package com.vica.trips.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vica.trips.models.Photo;
import com.vica.trips.repositories.PhotosRepository;
import com.vica.trips.services.FileService;
import com.vica.trips.services.FilesAndMetaDataService;
import com.vica.trips.services.PhotosMetaDataService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/files")
public class FilesController {

	@Autowired
    private FilesAndMetaDataService filesAndMetaDataService;
     	
    @PostMapping("/upload")
    //public ResponseEntity<String> handleFileUpload( //for text response
    public ResponseEntity<Map<String, String>> handleFileUpload(  //for JSON response
            @RequestParam("photo") MultipartFile file,
            @RequestParam("locationID") String locationID,
            @RequestParam("people") String people,
            @RequestParam("date") String dateString) { //2023-01-16

        try {        	
        	
        	if (dateString == null || "".equals(dateString.trim()) ) {
        		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body( Map.of("error", "Please provide date in the format like 2023-01-16"));
        	}
        	
            //call service here
        	filesAndMetaDataService.uploadNewPhoto(  file,  locationID,  people, dateString);
        	
            //return ResponseEntity.ok("Uploaded: " + filename); //plain text
            return ResponseEntity.ok( Map.of("message", "Uploaded: " + file.getName()) );
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body( Map.of("error", "Failed to upload file") );
        }
    }
    
	}

