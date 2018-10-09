package com.vin.geomap.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

@Component
public class CityMapper {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private Map<String, LinkedHashSet<String>> citiesMap = new HashMap<>();

    public Map<String, LinkedHashSet<String>> getCitiesMap() {
        return citiesMap;
    }

    public void setCitiesMap(Map<String, LinkedHashSet<String>> citiesMap) {
        this.citiesMap = citiesMap;
    }

}
