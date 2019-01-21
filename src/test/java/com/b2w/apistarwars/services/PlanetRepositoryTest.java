package com.b2w.apistarwars.services;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.b2w.apistarwars.models.Planet;
import com.b2w.apistarwars.repository.PlanetRepository;

public class PlanetRepositoryTest {

	private PlanetService planetService;

	@Mock
	private PlanetRepository planetRepository;

	@Before
	public void setUp() {
		planetRepository = Mockito.mock(PlanetRepository.class);
		planetService = new PlanetService(planetRepository);

	}

	@Test
	public void insertTest() {
		Planet planeta = new Planet("Endor", "temperate", "forests, mountains, lakes");
		planeta.setId("Bluf");
		when(planetRepository.save(planeta)).thenReturn(planeta);

		Planet planetReturn = planetService.insert(planeta);
		Assert.assertEquals(planetReturn.getName(), planeta.getName());
	}

	@Test
	public void findAllTest() {
		Planet planet1 = new Planet("Endor", "temperate", "forests, mountains, lakes");
		Planet planet2 = new Planet("Naboo", "temperate", "grassy hills, swamps, forests, mountains");
		Planet planet3 = new Planet("Coruscant", "temperate", "cityscape, mountains");
		List<Planet> planetas = new ArrayList<Planet>();
		planetas.add(planet1);
		planetas.add(planet2);
		planetas.add(planet3);
		when(planetRepository.findAll()).thenReturn(planetas);
		List<Planet> planetsReturn = planetService.findAll();
		Assert.assertEquals(planetsReturn.get(0).getName(), planet1.getName());
	}

	@Test
	public void findByIdTest() {
		Planet planet1 = new Planet("Endor", "temperate", "forests, mountains, lakes");
		Optional<Planet> planetaOpt = Optional.of(planet1);
		planet1.setId("Teste");
		when(planetRepository.findById(planet1.getId())).thenReturn(planetaOpt);

		Planet planetasRetorno = planetService.findById(planet1.getId());
		Assert.assertEquals(planetaOpt.get(), planetasRetorno);
	}

	@Test
	public void findByNameTest() {
		Planet planet1 = new Planet("Endor", "temperate", "forests, mountains, lakes");
		Planet planet2 = new Planet("Naboo", "temperate", "grassy hills, swamps, forests, mountains");
		Planet planet3 = new Planet("Coruscant", "temperate", "cityscape, mountains");
		List<Planet> planetas = new ArrayList<Planet>();
		planetas.add(planet1);
		planetas.add(planet2);
		planetas.add(planet3);
		when(planetRepository.findByNameContaining("Endor")).thenReturn(planetas);
		List<Planet> planetasRetorno = planetService.findByName("Endor");
		Assert.assertEquals(planetasRetorno.get(0).getName(), planet1.getName());
	}
}
