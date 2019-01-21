package com.b2w.apistarwars;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.b2w.apistarwars.models.StarWarsApiConstants;

@SpringBootApplication
public class DesafioB2WRestfulWebApp implements CommandLineRunner {
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(DesafioB2WRestfulWebApp.class);
 
	public static void main(String[] args) {
		SpringApplication.run(DesafioB2WRestfulWebApp.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		LOGGER.info(StarWarsApiConstants.LOAD_APP);
	}

}
