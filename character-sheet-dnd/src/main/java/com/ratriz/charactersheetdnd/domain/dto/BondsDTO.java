package com.ratriz.charactersheetdnd.domain.dto;

import com.ratriz.charactersheetdnd.domain.entity.Bonds;

public record BondsDTO(Long key, BackgroundDTO background, String name, String description, boolean inactive)
		implements IDTO<Bonds> {

	public BondsDTO(Long key) {
		this(key, null, null, null);
	}

	public BondsDTO(BackgroundDTO background, String name, String description) {
		this(null, background, name, description);
	}

	public BondsDTO(Long key, BackgroundDTO background, String name, String description) {
		this(key, background, name, description, false);
	}

	public BondsDTO(Bonds entity) {
		this(entity.getKey(), entity.getBackground().toDTO(), entity.getName(), entity.getDescription(),
				entity.isInactive());
	}

	@Override
	public Bonds toEntity() {
		return new Bonds(this);
	}
}
