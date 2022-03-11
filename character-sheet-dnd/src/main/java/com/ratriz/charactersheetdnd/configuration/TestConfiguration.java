package com.ratriz.charactersheetdnd.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ratriz.charactersheetdnd.service.DBService;

@Configuration
@Profile("test")
public class TestConfiguration {
	
	@Autowired
	private DBService dbService;

	@Bean
	public boolean instantiateDatabase() {
		dbService.instantiateDevDatabase();
		return true;
	}

}
