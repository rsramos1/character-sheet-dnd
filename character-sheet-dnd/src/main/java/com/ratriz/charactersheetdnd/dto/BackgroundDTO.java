package com.ratriz.charactersheetdnd.dto;

import com.ratriz.charactersheetdnd.domain.Background;

public record BackgroundDTO(Long id, String name, String description, boolean inactive) implements IDTO<Background> {

	public BackgroundDTO(Background entity) {
		this(entity.getId(), entity.getName(), entity.getDescription(), entity.isInactive());
	}

	@Override
	public Background toEntity() {
		return Background.of(id, name, description, inactive);
	}
}
