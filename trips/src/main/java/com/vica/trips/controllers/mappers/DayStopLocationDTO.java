package com.vica.trips.controllers.mappers;

public class DayStopLocationDTO {

    private int dayorder;
    private Integer id;	
	private String name;
	private String latitude;	
	private String longitude;
	
	public DayStopLocationDTO( int dayorder, Integer id, String name, 
			                        String latitude, String longitude) {
		this.dayorder = dayorder;
		this.id = id;
        this.name = name; 
        this.latitude= latitude;
        this.longitude = longitude;
	}
	
	public int getDayorder() {
		return dayorder;
	}
	public void setDayorder(int dayorder) {
		this.dayorder = dayorder;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
}
