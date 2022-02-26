package com.ratriz.charactersheetdnd;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ratriz.charactersheetdnd.domain.Background;
import com.ratriz.charactersheetdnd.repository.BackgroundRepository;

@SpringBootApplication
public class CharacterSheetDndApplication implements CommandLineRunner {

	@Autowired
	private BackgroundRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(CharacterSheetDndApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repository
				.saveAll(Arrays.asList(Background.builder().name("Criminoso").description("Que cometeu crime").build(),
						Background.builder().name("Artista").description("Que faz arte").build(),
						Background.builder().name("Orfão").description("Que não tem pais").build(),
						Background.builder().name("Artesão da Guilda").description("Que faz coisas pra guilda")
								.inactive(true).build(),
						Background.builder().name("Eremita").description("Que é um ermitão").inactive(true).build()));
	}

}
