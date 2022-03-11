package com.ratriz.charactersheetdnd.dto;

import com.ratriz.charactersheetdnd.domain.Hair;

public record HairDTO(Long key, String name, boolean inactive) implements IDTO<Hair> {

	public HairDTO(Long key) {
		this(key, null);
	}

	public HairDTO(String name) {
		this(null, name);
	}

	public HairDTO(Long key, String name) {
		this(key, name, false);
	}

	public HairDTO(Hair entity) {
		this(entity.getKey(), entity.getName(), entity.isInactive());
	}

	@Override
	public Hair toEntity() {
		return new Hair(this);
	}
}
