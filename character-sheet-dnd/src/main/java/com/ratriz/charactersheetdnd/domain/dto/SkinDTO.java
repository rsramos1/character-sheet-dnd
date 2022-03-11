package com.ratriz.charactersheetdnd.domain.dto;

import com.ratriz.charactersheetdnd.domain.entity.Skin;

public record SkinDTO(Long key, String name, boolean inactive) implements IDTO<Skin> {

	public SkinDTO(Long key) {
		this(key, null);
	}

	public SkinDTO(String name) {
		this(null, name);
	}

	public SkinDTO(Long key, String name) {
		this(key, name, false);
	}

	public SkinDTO(Skin entity) {
		this(entity.getKey(), entity.getName(), entity.isInactive());
	}

	@Override
	public Skin toEntity() {
		return new Skin(this);
	}
}
