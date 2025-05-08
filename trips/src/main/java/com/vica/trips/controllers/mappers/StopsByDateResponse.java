package com.vica.trips.controllers.mappers;

import java.util.ArrayList;
import java.util.List;

public class StopsByDateResponse {

	private String date = "";
	private List<DayStopLocationDTO> listStops = new ArrayList<DayStopLocationDTO>();
		
	public StopsByDateResponse(String date, List<DayStopLocationDTO> listStops) {
		this.date = date;
		this.listStops = listStops;
	}
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<DayStopLocationDTO> getListStops() {
		return listStops;
	}

	public void setListStops(List<DayStopLocationDTO> listStops) {
		this.listStops = listStops;
	}

}
