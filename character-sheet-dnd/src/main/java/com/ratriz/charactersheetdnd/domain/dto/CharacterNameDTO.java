package com.ratriz.charactersheetdnd.domain.dto;

import java.util.List;

import com.ratriz.charactersheetdnd.domain.entity.CharacterName;
import com.ratriz.charactersheetdnd.domain.entity.Subrace;

public record CharacterNameDTO(Long key, String name, Character gender, List<Subrace> subraces, boolean inactive)
		implements IDTO<CharacterName> {

	public CharacterNameDTO(Long key) {
		this(key, null, null, null);
	}

	public CharacterNameDTO(String name, Character gender, List<Subrace> subraces) {
		this(null, name, gender, subraces);
	}

	public CharacterNameDTO(Long key, String name, Character gender, List<Subrace> subraces) {
		this(key, name, gender, subraces, false);
	}

	public CharacterNameDTO(CharacterName entity) {
		this(entity.getKey(), entity.getName(), entity.getGender(), entity.getSubraces(), entity.isInactive());
	}

	@Override
	public CharacterName toEntity() {
		return new CharacterName(this);
	}
}
