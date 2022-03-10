package com.ratriz.charactersheetdnd.dto;

import com.ratriz.charactersheetdnd.domain.Bonds;

public record BondsDTO(Long key, String name, BackgroundDTO background, String description, boolean inactive)
		implements IDTO<Bonds> {

	public BondsDTO(Bonds entity) {
		this(entity.getKey(), entity.getName(), entity.getBackground().toDTO(), entity.getDescription(),
				entity.isInactive());
	}

	@Override
	public Bonds toEntity() {
		return new Bonds(this);
	}
}
