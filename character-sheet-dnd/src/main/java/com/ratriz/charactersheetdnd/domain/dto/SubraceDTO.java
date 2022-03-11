package com.ratriz.charactersheetdnd.domain.dto;

import com.ratriz.charactersheetdnd.domain.entity.Subrace;

public record SubraceDTO(Long key, RaceDTO race, String name, String description, boolean inactive)
		implements IDTO<Subrace> {

	public SubraceDTO(Long key) {
		this(key, null, null, null);
	}

	public SubraceDTO(RaceDTO race, String name, String description) {
		this(null, race, name, description);
	}

	public SubraceDTO(Long key, RaceDTO race, String name, String description) {
		this(key, race, name, description, false);
	}

	public SubraceDTO(Subrace entity) {
		this(entity.getKey(), entity.getRace().toDTO(), entity.getName(), entity.getDescription(), entity.isInactive());
	}

	@Override
	public Subrace toEntity() {
		return new Subrace(this);
	}
}
