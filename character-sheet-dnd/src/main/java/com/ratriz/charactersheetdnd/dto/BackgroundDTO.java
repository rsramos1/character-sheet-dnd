package com.ratriz.charactersheetdnd.dto;

import com.ratriz.charactersheetdnd.domain.Background;

public record BackgroundDTO(Long key, String name, String description, boolean inactive) implements IDTO<Background> {

	public BackgroundDTO(Background entity) {
		this(entity.getKey(), entity.getName(), entity.getDescription(), entity.isInactive());
	}

	@Override
	public Background toEntity() {
		return new Background(this);
	}
}
