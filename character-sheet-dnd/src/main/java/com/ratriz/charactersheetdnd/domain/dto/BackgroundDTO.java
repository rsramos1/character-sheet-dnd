package com.ratriz.charactersheetdnd.domain.dto;

import com.ratriz.charactersheetdnd.domain.entity.Background;

public record BackgroundDTO(Long key, String name, String description, String backgroundType, boolean inactive)
		implements IDTO<Background> {

	public BackgroundDTO(Long key) {
		this(key, null, null, null);
	}

	public BackgroundDTO(String name, String description, String backgroundType) {
		this(null, name, description, backgroundType);
	}

	public BackgroundDTO(Long key, String name, String description, String backgroundType) {
		this(key, name, description, backgroundType, false);
	}

	public BackgroundDTO(Background entity) {
		this(entity.getKey(), entity.getName(), entity.getDescription(), entity.getBackgroundType(),
				entity.isInactive());
	}

	@Override
	public Background toEntity() {
		return new Background(this);
	}
}
