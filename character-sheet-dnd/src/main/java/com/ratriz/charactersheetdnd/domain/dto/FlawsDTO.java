package com.ratriz.charactersheetdnd.domain.dto;

import com.ratriz.charactersheetdnd.domain.entity.Flaws;

public record FlawsDTO(Long key, BackgroundDTO background, String name, String description, boolean inactive)
		implements IDTO<Flaws> {

	public FlawsDTO(Long key) {
		this(key, null, null, null);
	}

	public FlawsDTO(BackgroundDTO background, String name, String description) {
		this(null, background, name, description);
	}

	public FlawsDTO(Long key, BackgroundDTO background, String name, String description) {
		this(key, background, name, description, false);
	}

	public FlawsDTO(Flaws entity) {
		this(entity.getKey(), entity.getBackground().toDTO(), entity.getName(), entity.getDescription(),
				entity.isInactive());
	}

	@Override
	public Flaws toEntity() {
		return new Flaws(this);
	}
}
