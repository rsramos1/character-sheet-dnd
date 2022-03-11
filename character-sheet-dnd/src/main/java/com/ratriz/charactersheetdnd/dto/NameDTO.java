package com.ratriz.charactersheetdnd.dto;

import com.ratriz.charactersheetdnd.domain.Name;

public record NameDTO(Long key, String name, Character gender, boolean inactive) implements IDTO<Name> {

	public NameDTO(Long key) {
		this(key, null, null);
	}

	public NameDTO(String name, Character gender) {
		this(null, name, gender);
	}

	public NameDTO(Long key, String name, Character gender) {
		this(key, name, gender, false);
	}

	public NameDTO(Name entity) {
		this(entity.getKey(), entity.getName(), entity.getGender(), entity.isInactive());
	}

	@Override
	public Name toEntity() {
		return new Name(this);
	}
}
