package com.ratriz.charactersheetdnd;

import java.util.List;

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
				.saveAll(List.of(
						Background.of("Criminoso","Que cometeu crime"),
						Background.of("Artista","Que faz arte"),
						Background.of("Orfão","Que não tem pais"),
						Background.of("Artesão da Guilda","Que faz coisas pra guilda"),
						Background.of("Eremita","Que é um ermitão")));
	}

}
