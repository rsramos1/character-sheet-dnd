package com.ratriz.charactersheetdnd.dto;

import com.ratriz.charactersheetdnd.domain.Ideals;

public record IdealsDTO(Long key, BackgroundDTO background, String name, String description, boolean inactive)
		implements IDTO<Ideals> {

	public IdealsDTO(Long key) {
		this(null, null, null);
	}

	public IdealsDTO(BackgroundDTO background, String name, String description) {
		this(null, background, name, description);
	}

	public IdealsDTO(Long key, BackgroundDTO background, String name, String description) {
		this(key, background, name, description, false);
	}

	public IdealsDTO(Ideals entity) {
		this(entity.getKey(), entity.getBackground().toDTO(), entity.getName(), entity.getDescription(),
				entity.isInactive());
	}

	@Override
	public Ideals toEntity() {
		return new Ideals(this);
	}
}
