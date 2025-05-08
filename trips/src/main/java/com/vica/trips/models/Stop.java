package com.vica.trips.models;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "STOPS")
public class Stop {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "date") 
	private LocalDate date;
    
	private int dayorder;		
	private boolean marker; // BIT DEFAULT 1	
    
    @ManyToOne
    @JoinColumn(name = "locID")  // Foreign key column
    private Location location;   //each stop can have only one location
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public int getDayorder() {
		return dayorder;
	}
	public void setDayorder(int dayorder) {
		this.dayorder = dayorder;
	}
	
	public boolean isMarker() {
		return marker;
	}
	public void setMarker(boolean marker) {
		this.marker = marker;
	}
	
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
}
