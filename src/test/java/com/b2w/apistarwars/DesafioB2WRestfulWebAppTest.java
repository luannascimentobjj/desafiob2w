package com.b2w.apistarwars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.b2w.apistarwars.models.Planet;
import com.b2w.apistarwars.models.StarWarsApiConstants;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class DesafioB2WRestfulWebAppTest {
	

	@LocalServerPort
	private int port;
	
	RestTemplate rest;
		
	@Before
	public void setUp() {
		rest = new RestTemplate();
	}

	@Test
	public void insertPlanetTest() {

		Planet planeta = new Planet("Yavin IV", "temperate, tropical", "jungle, rainforests");
		ResponseEntity<String> response = rest.postForEntity(StarWarsApiConstants.BASE_PATH + port + "/planets/",
				planeta, String.class);
		Assert.assertEquals(201, response.getStatusCodeValue());
		rest.delete(response.getHeaders().getLocation());

	}

	@Test
	public void insertEmptyNameTest() {
		Planet planet = new Planet("", "temperate, tropical", "jungle, rainforests");
		try {
			rest.postForEntity(StarWarsApiConstants.BASE_PATH + port + "/planets/", planet, String.class);
		} catch (Exception e) {
			Assert.assertEquals("400 null", e.getMessage());
		}
	}

	@Test
	public void insertEmptyClimateTest() {
		Planet planet = new Planet("Yavin IV", "", "jungle, rainforests");
		try {
			rest.postForEntity(StarWarsApiConstants.BASE_PATH + port + "/planets/", planet, String.class);
		} catch (Exception e) {
			Assert.assertEquals("400 null", e.getMessage());
		}
	}
	 
	@Test
	public void insertEmptyTerrainTest() {
		Planet planet = new Planet("Yavin IV", "temperate, tropical", "");
		try {
			rest.postForEntity(StarWarsApiConstants.BASE_PATH + port + "/planets/", planet, String.class);
		} catch (Exception e) {
			Assert.assertEquals("400 null", e.getMessage());
		}
	}
	
	@Test
	public void insertNullNameTest() {
		Planet planet = new Planet(null, "temperate, tropical", "jungle, rainforests");
		try {
			rest.postForEntity(StarWarsApiConstants.BASE_PATH + port + "/planets/", planet, String.class);
		} catch (Exception e) {
			Assert.assertEquals("400 null", e.getMessage());
		}
	}
	
	@Test
	public void insertNullClimateTest() {
		Planet planet = new Planet("Yavin IV", null, "jungle, rainforests");
		try {
			rest.postForEntity(StarWarsApiConstants.BASE_PATH + port + "/planets/", planet, String.class);
		} catch (Exception e) {
			Assert.assertEquals("400 null", e.getMessage());
		}
	}
	
	@Test
	public void insertNullTerrainTest() {
		Planet planets = new Planet("Yavin IV", "temperate, tropical", null);
		try {
			rest.postForEntity(StarWarsApiConstants.BASE_PATH + port + "/planets/", planets, String.class);
		} catch (Exception e) {
			Assert.assertEquals("400 null", e.getMessage());
		}
	}

	@Test
	public void findByIdTest() {
		Planet planeta = new Planet("Yavin IV", "temperate, tropical", "jungle, rainforests");
		ResponseEntity<String> response = rest.postForEntity(StarWarsApiConstants.BASE_PATH + port + "/planets/",
				planeta, String.class);

		ResponseEntity<String> findResponse = rest.getForEntity(response.getHeaders().getLocation(), String.class);
		Assert.assertEquals(200, findResponse.getStatusCodeValue());

		rest.delete(response.getHeaders().getLocation());
	}
	
	@Test
	public void findByIdNotExistTest() {
		try {
			rest.getForEntity(StarWarsApiConstants.BASE_PATH + port + "/planets/?id=test", String.class);
		} catch (Exception e) {
			Assert.assertEquals("404 null", e.getMessage());
		}
	}
	
	@Test
	public void findByNameTest() {
		Planet planeta = new Planet("Yavin IV","temperate, tropical", "jungle, rainforests");
		
		ResponseEntity<String>  response = rest.postForEntity(StarWarsApiConstants.BASE_PATH + port +"/planets/",planeta,String.class);
		ResponseEntity<String>  findResponse = rest.getForEntity(StarWarsApiConstants.BASE_PATH + port +"/planets/?nome=Yavin IV", String.class);
		Assert.assertEquals(200, findResponse.getStatusCodeValue());
		
		rest.delete(response.getHeaders().getLocation());

	}
	
	@Test
	public void findByNameNotExistTest() {
		ResponseEntity<String> response = rest.getForEntity(StarWarsApiConstants.BASE_PATH + port +"/planets/?name=Teste", String.class);
		Assert.assertEquals(200, response.getStatusCodeValue());
	}

	@Test
	public void findAllTest() {
		Planet planet1 = new Planet("Yavin IV", "temperate, tropical", "jungle, rainforests");
		Planet planet2 = new Planet("Alderaan", "temperate", "grasslands, mountains");
		Planet planet3 = new Planet("Hoth", "frozen", "tundra, ice caves, mountain ranges");
		List<Planet> planets = new ArrayList<Planet>();
		planets.add(planet1);
		planets.add(planet2);
		planets.add(planet3);
		ResponseEntity<String> response = rest.getForEntity(StarWarsApiConstants.BASE_PATH + port + "/planets/",
				String.class);
		Assert.assertEquals(200, response.getStatusCodeValue());
	}

	@Test
	public void deleteTest() {
		Planet planeta = new Planet("Yavin IV", "temperate, tropical", "jungle, rainforests");
		ResponseEntity<String> response = rest.postForEntity(StarWarsApiConstants.BASE_PATH + port + "/planets/",
				planeta, String.class);

		ResponseEntity<String> respostaBusca = rest.exchange(response.getHeaders().getLocation().toString(),
				HttpMethod.DELETE, buildHeader(), String.class, planeta);
		Assert.assertEquals(204, respostaBusca.getStatusCodeValue());
	}

	@Test
	public void deleteNotExistTest() {
		try {
			rest.exchange(StarWarsApiConstants.BASE_PATH + port + "/planets/" + "Test", HttpMethod.DELETE,
					buildHeader(), String.class);
		} catch (Exception e) {
			Assert.assertEquals("404 null", e.getMessage());
		}
	}
	
	private HttpEntity<String> buildHeader() {
	    HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add(StarWarsApiConstants.USER_AGENT, StarWarsApiConstants.HEADER_TEMPLATE);
        HttpEntity<String> entity = new HttpEntity<String>(StarWarsApiConstants.PARAMETERS, headers);
		return entity;
	}
}
