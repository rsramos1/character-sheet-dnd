package com.ratriz.charactersheetdnd.domain.dto;

import java.util.List;

import com.ratriz.charactersheetdnd.domain.entity.Eyes;
import com.ratriz.charactersheetdnd.domain.entity.Subrace;

public record EyesDTO(Long key, String name, List<Subrace> subraces, boolean inactive) implements IDTO<Eyes> {

	public EyesDTO(Long key) {
		this(key, null, null);
	}

	public EyesDTO(String name, List<Subrace> subraces) {
		this(null, name, subraces);
	}

	public EyesDTO(Long key, String name, List<Subrace> subraces) {
		this(key, name, subraces, false);
	}

	public EyesDTO(Eyes entity) {
		this(entity.getKey(), entity.getName(), entity.getSubraces(), entity.isInactive());
	}

	@Override
	public Eyes toEntity() {
		return new Eyes(this);
	}
}
