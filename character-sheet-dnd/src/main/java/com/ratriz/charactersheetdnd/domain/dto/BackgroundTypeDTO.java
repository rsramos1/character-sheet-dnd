package com.ratriz.charactersheetdnd.domain.dto;

import com.ratriz.charactersheetdnd.domain.entity.BackgroundType;

public record BackgroundTypeDTO(Long key, BackgroundDTO background, String name, String description, boolean inactive)
		implements IDTO<BackgroundType> {

	public BackgroundTypeDTO(Long key) {
		this(key, null, null, null);
	}

	public BackgroundTypeDTO(BackgroundDTO background, String name, String description) {
		this(null, background, name, description);
	}

	public BackgroundTypeDTO(Long key, BackgroundDTO background, String name, String description) {
		this(key, background, name, description, false);
	}

	public BackgroundTypeDTO(BackgroundType entity) {
		this(entity.getKey(), entity.getBackground().toDTO(), entity.getName(), entity.getDescription(),
				entity.isInactive());
	}

	@Override
	public BackgroundType toEntity() {
		return new BackgroundType(this);
	}
}
