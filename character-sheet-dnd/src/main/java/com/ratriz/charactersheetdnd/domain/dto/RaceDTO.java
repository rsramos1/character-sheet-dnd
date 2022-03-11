package com.ratriz.charactersheetdnd.domain.dto;

import com.ratriz.charactersheetdnd.domain.entity.Race;

public record RaceDTO(Long key, String name, String description, boolean inactive) implements IDTO<Race> {

	public RaceDTO(Long key) {
		this(key, null, null);
	}

	public RaceDTO(String name, String description) {
		this(null, name, description);
	}

	public RaceDTO(Long key, String name, String description) {
		this(key, name, description, false);
	}

	public RaceDTO(Race entity) {
		this(entity.getKey(), entity.getName(), entity.getDescription(), entity.isInactive());
	}

	@Override
	public Race toEntity() {
		return new Race(this);
	}
}
