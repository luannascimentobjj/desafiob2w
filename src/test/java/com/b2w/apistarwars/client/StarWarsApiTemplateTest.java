package com.b2w.apistarwars.client;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.b2w.apistarwars.models.ApiSwarWarsResult;



public class StarWarsApiTemplateTest {
    
    @Autowired
    StarWarsApiTemplate rest;

    @Before
	public void setUp() {
		rest = new StarWarsApiTemplate();
	}
    
	@Test
    public void findAppearsTest() {
		ResponseEntity<ApiSwarWarsResult> result = rest.returnAppears();
    	Assert.assertNotNull(result);
     }
}
 