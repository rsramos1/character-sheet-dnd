package com.ratriz.charactersheetdnd.dto;

import com.ratriz.charactersheetdnd.domain.Bonds;

public record BondsDTO(Long id, String name, BackgroundDTO background, String description, boolean inactive)
		implements IDTO<Bonds> {

	public BondsDTO(Bonds entity) {
		this(entity.getId(), entity.getName(), entity.getBackground().toDTO(), entity.getDescription(),
				entity.isInactive());
	}

	@Override
	public Bonds toEntity() {
		return Bonds.builder().id(id).name(name).background(background.toEntity()).description(description)
				.inactive(inactive).build();
	}
}
