package com.vin.geomap.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GeomapApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	private MockMvc mockMvc;

	@Test
	public void isPhiladelphiaAndNewarkConnectedYes() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/connected/?origin=Philadelphia&destination=Newark"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("text/plain;charset=UTF-8"))
				.andExpect(content().string("yes"));
	}

	@Test
	public void isPathTwoWayBetweenPhiladelphiaAndNewark() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/connected")
				.param("origin","Newark")
				.param("destination","Philadelphia"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("text/plain;charset=UTF-8"))
				.andExpect(content().string("yes"));
	}

	@Test
	public void isHobokenAndLasVegasConnectedNo() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/connected/?origin=hoboken&destination=lasVegas"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("text/plain;charset=UTF-8"))
				.andExpect(content().string("no"));
	}

	@Test
	public void OnlyOneParamPassedShouldFailWith400Error() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/connected")
				.param("origin","Newark"))
				.andExpect(status().is4xxClientError());
	}


}
