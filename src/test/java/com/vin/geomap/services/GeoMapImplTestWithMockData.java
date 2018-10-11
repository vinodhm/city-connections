package com.vin.geomap.services;

import com.vin.geomap.data.CityMapper;
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
public class GeoMapImplTestWithMockData {

    @Mock
    private CityMapper cityMapper;

    @InjectMocks
    private GeoMapImpl geoMap;

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

    @Test
    public void whenNoDataExist() throws Exception{
        when(cityMapper.getCitiesMap()).thenReturn(new HashMap<>());
        Assert.assertEquals(false,geoMap.checkConnectivityBetweenCities("Weehawken","newark"));
    }

    @Test
    public void whenOriginExistAndDestinationNot() throws Exception {

        Map<String, LinkedHashSet<String>> mockmap= new HashMap<>();
        LinkedHashSet<String> mockSet1 = new LinkedHashSet<>();
        mockmap.put("Weehawken",mockSet1);
        mockSet1.add("Hoboken");
        when(cityMapper.getCitiesMap()).thenReturn(mockmap);
        Assert.assertEquals(false,geoMap.checkConnectivityBetweenCities("Weehawken","LasVegas"));
    }

    @Test
    public void whenDestinationExistAndOrginNot() throws Exception {

        Map<String, LinkedHashSet<String>> mockmap= new HashMap<>();
        LinkedHashSet<String> mockSet1 = new LinkedHashSet<>();
        LinkedHashSet<String> mockSet2 = new LinkedHashSet<>();
        mockmap.put("Weehawken",mockSet1);
        mockSet1.add("Hoboken");
        mockmap.put("Hoboken",mockSet2);
        mockSet2.add("Weehawken");
        when(cityMapper.getCitiesMap()).thenReturn(mockmap);
        Assert.assertEquals(false,geoMap.checkConnectivityBetweenCities("chicago","Hoboken"));
    }

    @Test
    /*Since our assumption is all the routes are biDirectional MapShould have both the routes, it should return false*/
    public void whenOnlyOneWayExist() throws Exception {

        Map<String, LinkedHashSet<String>> mockmap= new HashMap<>();
        LinkedHashSet<String> mockSet1 = new LinkedHashSet<>();
        mockmap.put("Weehawken",mockSet1);
        mockSet1.add("Hoboken");
        when(cityMapper.getCitiesMap()).thenReturn(mockmap);
        Assert.assertEquals(false,geoMap.checkConnectivityBetweenCities("Weehawken","Hoboken"));
    }

}