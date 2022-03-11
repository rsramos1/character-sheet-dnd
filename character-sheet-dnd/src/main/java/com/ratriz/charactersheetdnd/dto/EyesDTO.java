package com.ratriz.charactersheetdnd.dto;

import com.ratriz.charactersheetdnd.domain.Eyes;

public record EyesDTO(Long key, String name, boolean inactive) implements IDTO<Eyes> {

	public EyesDTO(Long key) {
		this(key, null);
	}

	public EyesDTO(String name) {
		this(null, name);
	}

	public EyesDTO(Long key, String name) {
		this(key, name, false);
	}

	public EyesDTO(Eyes entity) {
		this(entity.getKey(), entity.getName(), entity.isInactive());
	}

	@Override
	public Eyes toEntity() {
		return new Eyes(this);
	}
}
