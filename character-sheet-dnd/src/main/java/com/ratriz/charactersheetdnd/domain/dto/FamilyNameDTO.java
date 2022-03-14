package com.ratriz.charactersheetdnd.domain.dto;

import java.util.List;

import com.ratriz.charactersheetdnd.domain.entity.FamilyName;
import com.ratriz.charactersheetdnd.domain.entity.Subrace;

public record FamilyNameDTO(Long key, String name, List<Subrace> subraces, boolean inactive)
		implements IDTO<FamilyName> {

	public FamilyNameDTO(Long key) {
		this(key, null, null);
	}

	public FamilyNameDTO(String name, List<Subrace> subraces) {
		this(null, name, subraces);
	}

	public FamilyNameDTO(Long key, String name, List<Subrace> subraces) {
		this(key, name, subraces, false);
	}

	public FamilyNameDTO(FamilyName entity) {
		this(entity.getKey(), entity.getName(), entity.getSubraces(), entity.isInactive());
	}

	@Override
	public FamilyName toEntity() {
		return new FamilyName(this);
	}
}
