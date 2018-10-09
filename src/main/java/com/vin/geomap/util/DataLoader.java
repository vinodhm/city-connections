package com.vin.geomap.util;

import com.vin.geomap.data.CityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;

@Component
public class DataLoader {

    Logger log = LoggerFactory.getLogger(this.getClass());

    private String fileName="city.txt";

    CityMapper cityMapper;

    @Autowired
    public DataLoader(CityMapper cityMapper){
        try {
            this.cityMapper=cityMapper;
            loadCSVFile(fileName);
        } catch (IOException e) {
            log.error("Unable to load City.txt File:",e);
        }
    }

    public void addCity(String city1, String city2){
        LinkedHashSet<String> adjacentCity = cityMapper.getCitiesMap().get(city1);
        if (adjacentCity == null){
            adjacentCity = new LinkedHashSet<String>();
            cityMapper.getCitiesMap().put(city1, adjacentCity);
        }
        adjacentCity.add(city2);
    }

    public void addTwoWayCity(String city1, String city2){
        addCity(city1, city2);
        addCity(city2, city1);
    }

    public void loadCSVFile(String fileName) throws IOException{

        InputStream resource = new ClassPathResource(fileName).getInputStream();

        try ( BufferedReader reader = new BufferedReader(
                new InputStreamReader(resource)) ) {
            reader.lines().forEach(line->{
                String[] cities= line.split(",");
                log.info("cities[0]:"+cities[0]+"; cities[1]"+cities[1]);
                addTwoWayCity(cities[0].trim(),cities[1].trim());
            });
        }
    }




    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
