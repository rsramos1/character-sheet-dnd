package com.ratriz.charactersheetdnd.domain.dto;

import com.ratriz.charactersheetdnd.domain.entity.CharacterClass;

public record CharacterClassDTO(Long key, String name, String description, boolean inactive)
		implements IDTO<CharacterClass> {

	public CharacterClassDTO(Long key) {
		this(key, null, null);
	}

	public CharacterClassDTO(String name, String description) {
		this(null, name, description);
	}

	public CharacterClassDTO(Long key, String name, String description) {
		this(key, name, description, false);
	}

	public CharacterClassDTO(CharacterClass entity) {
		this(entity.getKey(), entity.getName(), entity.getDescription(), entity.isInactive());
	}

	@Override
	public CharacterClass toEntity() {
		return new CharacterClass(this);
	}
}
