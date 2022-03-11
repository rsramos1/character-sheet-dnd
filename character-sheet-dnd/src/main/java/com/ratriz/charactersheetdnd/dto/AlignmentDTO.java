package com.ratriz.charactersheetdnd.dto;

import com.ratriz.charactersheetdnd.domain.Alignment;

public record AlignmentDTO(Long key, String name, String description, boolean inactive) implements IDTO<Alignment> {

	public AlignmentDTO(Long key) {
		this(key, null, null);
	}

	public AlignmentDTO(String name, String description) {
		this(null, name, description);
	}

	public AlignmentDTO(Long key, String name, String description) {
		this(key, name, description, false);
	}

	public AlignmentDTO(Alignment entity) {
		this(entity.getKey(), entity.getName(), entity.getDescription(), entity.isInactive());
	}

	@Override
	public Alignment toEntity() {
		return new Alignment(this);
	}
}
