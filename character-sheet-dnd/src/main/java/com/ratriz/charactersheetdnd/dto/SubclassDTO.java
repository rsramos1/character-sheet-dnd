package com.ratriz.charactersheetdnd.dto;

import com.ratriz.charactersheetdnd.domain.Subclass;

public record SubclassDTO(Long key, CharacterClassDTO characterClass, String name, String description, boolean inactive)
		implements IDTO<Subclass> {

	public SubclassDTO(Long key) {
		this(key, null, null, null);
	}

	public SubclassDTO(CharacterClassDTO characterClass, String name, String description) {
		this(null, characterClass, name, description);
	}

	public SubclassDTO(Long key, CharacterClassDTO characterClass, String name, String description) {
		this(key, characterClass, name, description, false);
	}

	public SubclassDTO(Subclass entity) {
		this(entity.getKey(), entity.getCharacterClass().toDTO(), entity.getName(), entity.getDescription(),
				entity.isInactive());
	}

	@Override
	public Subclass toEntity() {
		return new Subclass(this);
	}
}
