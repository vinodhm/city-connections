package com.vin.geomap;

import com.vin.geomap.services.GeoMap;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GeomapApplicationTests {

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	GeoMap geoMap;

	@Test
	public void toCheckConnectionInSameTree() {
		Assert.assertEquals(true,geoMap.checkConnectivityBetweenCities("Hoboken","north Bergen"));
	}

	@Test
	public void toCheckConnectionCitiesNotInSameTree() {
		Assert.assertEquals(false,geoMap.checkConnectivityBetweenCities("Hoboken","newark"));
	}

	@Test
	public void toCheckifOppositeDirectionConnectionExist() {
		Assert.assertEquals(true,geoMap.checkConnectivityBetweenCities("north Bergen","Hoboken"));
	}

	@Test
	public void toCheckifSourceAndDestinationAreSame() {
		Assert.assertEquals(true,geoMap.checkConnectivityBetweenCities("Hoboken","Hoboken"));
	}


}
