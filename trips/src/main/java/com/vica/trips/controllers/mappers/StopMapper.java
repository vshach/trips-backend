package com.vica.trips.controllers.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.vica.trips.controllers.mappers.DayStopLocationDTO;
import com.vica.trips.models.Stop;

@Mapper(componentModel = "spring")
public interface StopMapper {

	    StopMapper INSTANCE = Mappers.getMapper(StopMapper.class);

	    @Mapping(source = "dayorder", target = "dayorder")
	    @Mapping(source = "location.id", target = "id")
	    @Mapping(source = "location.name", target = "name")
	    @Mapping(source = "location.latitude", target = "latitude")
	    @Mapping(source = "location.longitude", target = "longitude")
	    DayStopLocationDTO stopToCustomResponse(Stop stop);
	
}
