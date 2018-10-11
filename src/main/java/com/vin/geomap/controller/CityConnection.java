package com.vin.geomap.controller;

import com.vin.geomap.services.GeoMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "Verifies given cities connected or not based on information from input file.")
public class CityConnection{

    private final GeoMap geoMap;

    @Autowired
    public CityConnection(GeoMap geoMap) {
        this.geoMap = geoMap;
    }

    @GetMapping("/connected")
    @ApiOperation("Takes origin and destination as parameters and Returns yes if cities are connected, no if no connection between cities")
    public String isCitiesConnected(@RequestParam("origin") String origin, @RequestParam("destination") String destination){

        return geoMap.checkConnectivityBetweenCities(origin,destination)?"yes":"no";
    }
}
