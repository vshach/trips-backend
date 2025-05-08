package com.vica.trips.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "PHOTOS")
//@IdClass(PhotoId.class)
public class Photo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	//@Id
	private String filename;
	
	//@Id
	@Column(name = "date") 
	private LocalDate dateTaken;	
    
	@Column(name = "locationID")
	private int locationID;	
	
	@Column(name = "person") 
	private String people;	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLocationID() {
		return locationID;
	}
	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}
	public LocalDate getDateTaken() {
		return dateTaken;
	}
	public void setDateTaken(LocalDate dateTaken) {
		this.dateTaken = dateTaken;
	}
	public String getPeople() {
		return people;
	}
	public void setPeople(String people) {
		this.people = people;
	}
	public String getFilepath() {
		return filename;
	}
	public void setFilepath(String filepath) {
		this.filename = filepath;
	}
	
}
