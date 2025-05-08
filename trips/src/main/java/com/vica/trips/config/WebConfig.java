package com.vica.trips.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration   //do not forget this! This means that's configuration :)
public class WebConfig implements WebMvcConfigurer {

	@Value("${fileDirectory}")
    private String fileDirectory; 
	
	@Value("${serverPhotosURLpart}")
	private String serverPhotosURLpart;
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		Path uploadPath = Paths.get(fileDirectory).toAbsolutePath().normalize();
        String resourceLocation = uploadPath.toUri().toString();
        
        registry.addResourceHandler("/"+serverPhotosURLpart+"/**")
                //.addResourceLocations("file:uploads/"); // or absolute path if needed
        		.addResourceLocations( resourceLocation );
	}
	
}