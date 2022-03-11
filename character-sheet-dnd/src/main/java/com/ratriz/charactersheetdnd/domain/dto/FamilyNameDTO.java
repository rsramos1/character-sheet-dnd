package com.ratriz.charactersheetdnd.domain.dto;

import com.ratriz.charactersheetdnd.domain.entity.FamilyName;

public record FamilyNameDTO(Long key, String name, boolean inactive) implements IDTO<FamilyName> {

	public FamilyNameDTO(Long key) {
		this(key, null);
	}

	public FamilyNameDTO(String name) {
		this(null, name);
	}

	public FamilyNameDTO(Long key, String name) {
		this(key, name, false);
	}

	public FamilyNameDTO(FamilyName entity) {
		this(entity.getKey(), entity.getName(), entity.isInactive());
	}

	@Override
	public FamilyName toEntity() {
		return new FamilyName(this);
	}
}
