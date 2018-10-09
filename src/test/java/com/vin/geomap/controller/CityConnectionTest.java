package com.vin.geomap.controller;

import com.vin.geomap.data.CityMapper;
import com.vin.geomap.services.GeoMapImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CityConnectionTest {

    @Mock
    CityMapper cityMapper;

    @InjectMocks
    GeoMapImpl geoMap;

    @Test
    public void isAdjacentCitiesConnected() throws Exception {
        Map<String, LinkedHashSet<String>> mockmap1= new HashMap<>();
        LinkedHashSet<String> mockSet1 = new LinkedHashSet<>();
        LinkedHashSet<String> mockSet2 = new LinkedHashSet<>();
        mockSet1.add("Hoboken");
        mockSet2.add("Weehawken");
        mockmap1.put("Hoboken",mockSet2 );
        mockmap1.put("Weehawken",mockSet1);
        when(cityMapper.getCitiesMap()).thenReturn(mockmap1);
        Assert.assertEquals(true,geoMap.checkConnectivityBetweenCities("Weehawken","Hoboken"));
    }

    @Test
    public void connectingCitiesConnected() throws Exception {

        Map<String, LinkedHashSet<String>> mockmap1= new HashMap<>();
        LinkedHashSet<String> mockSet1 = new LinkedHashSet<>();
        LinkedHashSet<String> mockSet2 = new LinkedHashSet<>();
        LinkedHashSet<String> mockSet3 = new LinkedHashSet<>();
        mockSet1.add("Hoboken");
        mockSet2.add("newark");
        mockSet2.add("Weehawken");
        mockSet3.add("Hoboken");
        mockmap1.put("Hoboken",mockSet2 );
        mockmap1.put("Weehawken",mockSet1);
        mockmap1.put("newark",mockSet3);

        when(cityMapper.getCitiesMap()).thenReturn(mockmap1);
        Assert.assertEquals(true,geoMap.checkConnectivityBetweenCities("Weehawken","newark"));

        Assert.assertEquals(true,geoMap.checkConnectivityBetweenCities("newark","Weehawken"));

    }

}