package com.vin.geomap.services;

import com.vin.geomap.data.CityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.LinkedList;

@Service
public class GeoMapImpl implements GeoMap {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CityMapper cityMapper;

    public boolean checkConnectivityBetweenCities(String origin,String destination ){
        LinkedList<String> visited = new LinkedList();
        visited.add(origin);

        if(cityMapper.getCitiesMap().get(origin)!=null && cityMapper.getCitiesMap().get(destination)!=null){
            if (origin.equalsIgnoreCase(destination)){
                return true;
            }
            return breadthFirstCitySearch(cityMapper.getCitiesMap().get(origin), visited,origin,destination);
        }else{
            logger.info("No path Exists between " + origin + " and " + destination);
            return false;
        }
    }

    private boolean breadthFirstCitySearch(LinkedHashSet<String> adjacentCities,
                                           LinkedList<String> visited,String origin,String destination){
        boolean flag=false;
        for (String adjacentCity : adjacentCities){
            if (visited.contains(adjacentCity)){
                continue;
            }
            if (adjacentCity.equalsIgnoreCase(destination)){
                visited.add(adjacentCity);
                printPath(visited);
                flag = true;
                break;
            }
        }

        if (!flag) {  // if flag is false then continue checking adjacent cities
            for (String adjacentCity : adjacentCities) {
                if (visited.contains(adjacentCity)) {
                    continue;
                }
                visited.addLast(adjacentCity);
                flag = breadthFirstCitySearch(cityMapper.getCitiesMap().get(adjacentCity), visited,origin,destination);
                visited.removeLast();
                if (flag){
                    return flag;
                }
            }
            if (flag == false) {
                return flag;
            }
        }
        return flag;
    }

    private void printPath(LinkedList<String> visited){
        logger.info("visited cities:"+visited);
    }

}
