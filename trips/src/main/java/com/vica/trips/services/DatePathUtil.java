package com.vica.trips.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatePathUtil {

    @Value("${fileDirectory}")
    private String fileDirectory;
    
	//from database to storage
	//2023-01-16  -> 2023/01Jan-16/ +filename will go here afterwards
    //this is for the path on the disk
	public String convertToFilepath( String dateString ) throws DateTimeParseException{
				
		System.out.println("fileDirectory: "+ fileDirectory );
		
		LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
		String datePath = convertToFilepath( date );
		String fullPath = "";
    	if (fileDirectory.endsWith("/"))
    		fullPath = fileDirectory + datePath;
    	else
    		fullPath = fileDirectory + "/"+datePath;
    	System.out.println("fullPath: "+ fullPath );
    	return fullPath;
	}	  
	
	//from database to storage
	//2023-01-16  -> 2023/01Jan-16/ +filename will go here afterwards
	//this is for the URL to serve the file
	public static String convertToFilepath( LocalDate date ) {
					
		StringBuffer sb = new StringBuffer( );
		sb.append( date.getYear() );
		sb.append( "/" );
		int monthVal = date.getMonthValue();
		if (monthVal<9)
			sb.append( "0" );
		sb.append( monthVal );
		sb.append( date.getMonth().getDisplayName( TextStyle.SHORT, Locale.CANADA));
		sb.append("-");
		if ( date.getDayOfMonth()<10)
			sb.append("0");
		sb.append( date.getDayOfMonth());
		sb.append( "/" );
		String datePath = sb.toString();				
		return datePath;
	}
}
