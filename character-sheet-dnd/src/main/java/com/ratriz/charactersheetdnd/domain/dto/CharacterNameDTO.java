package com.ratriz.charactersheetdnd.domain.dto;

import com.ratriz.charactersheetdnd.domain.entity.CharacterName;

public record CharacterNameDTO(Long key, String name, Character gender, boolean inactive) implements IDTO<CharacterName> {

	public CharacterNameDTO(Long key) {
		this(key, null, null);
	}

	public CharacterNameDTO(String name, Character gender) {
		this(null, name, gender);
	}

	public CharacterNameDTO(Long key, String name, Character gender) {
		this(key, name, gender, false);
	}

	public CharacterNameDTO(CharacterName entity) {
		this(entity.getKey(), entity.getName(), entity.getGender(), entity.isInactive());
	}

	@Override
	public CharacterName toEntity() {
		return new CharacterName(this);
	}
}
