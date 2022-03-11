package com.ratriz.charactersheetdnd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratriz.charactersheetdnd.domain.dto.BackgroundDTO;
import com.ratriz.charactersheetdnd.domain.dto.BondsDTO;

@Service
public class DBService {
	
	@Autowired
	private BackgroundService backgroundService;

	@Autowired
	private BondsService bondsService;
	
	public void instantiateDevDatabase() {
		List<BackgroundDTO> backgrounds = getBackgrounds();
		List<BondsDTO> bonds = getBonds(backgrounds);

		backgrounds.forEach(backgroundService::insert);
		bonds.forEach(bondsService::insert);
	}
	
	public List<BackgroundDTO> getBackgrounds() {
		return List.of(
				new BackgroundDTO(1L, "Criminoso", "Que cometeu crime", false),
				new BackgroundDTO(2L, "Artista", "Que faz arte", false),
				new BackgroundDTO(3L, "Orfão", "Que não tem pais", false),
				new BackgroundDTO(4L, "Artesão da Guilda", "Que faz coisas pra guilda", false),
				new BackgroundDTO(5L, "Eremita", "Que é um ermitão", false)
				);
	}
	
	public List<BondsDTO> getBonds(List<BackgroundDTO> backgrounds) {
		return List.of(
				new BondsDTO(1L, backgrounds.get(0), "teste", "testando", false)
				);
	}

}
