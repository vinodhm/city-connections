package com.vin.geomap.controller;

import com.vin.geomap.services.GeoMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityConnection{

    @Autowired
    GeoMap geoMap;

    @GetMapping("/connected")
    public String isCitiesConnected(@RequestParam("origin") String origin, @RequestParam("destination") String destination){

        return geoMap.checkConnectivityBetweenCities(origin,destination)?"yes":"no";
    }
}
