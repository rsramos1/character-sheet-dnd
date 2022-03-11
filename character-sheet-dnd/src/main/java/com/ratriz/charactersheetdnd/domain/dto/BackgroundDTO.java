package com.ratriz.charactersheetdnd.domain.dto;

import com.ratriz.charactersheetdnd.domain.entity.Background;

public record BackgroundDTO(Long key, String name, String description, boolean inactive) implements IDTO<Background> {

	public BackgroundDTO(Long key) {
		this(key, null, null);
	}

	public BackgroundDTO(String name, String description) {
		this(null, name, description);
	}

	public BackgroundDTO(Long key, String name, String description) {
		this(key, name, description, false);
	}

	public BackgroundDTO(Background entity) {
		this(entity.getKey(), entity.getName(), entity.getDescription(), entity.isInactive());
	}

	@Override
	public Background toEntity() {
		return new Background(this);
	}
}
