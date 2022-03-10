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
		repository.saveAll(getBackgroudns());
	}

	public List<Background> getBackgroudns() {
		Background criminoso = new Background();
		criminoso.setName("Criminoso");
		criminoso.setDescription("Que cometeu crime");

		Background artista = new Background();
		artista.setName("Artista");
		artista.setDescription("Que faz arte");

		Background orfao = new Background();
		orfao.setName("Orfão");
		orfao.setDescription("Que não tem pais");

		Background artesaoDaGuilda = new Background();
		artesaoDaGuilda.setName("Artesão da Guilda");
		artesaoDaGuilda.setDescription("Que faz coisas pra guilda");

		Background eremita = new Background();
		eremita.setName("Eremita");
		eremita.setDescription("Que é um ermitão");

		return List.of(criminoso, artista, orfao, artesaoDaGuilda, eremita);
	}

}
