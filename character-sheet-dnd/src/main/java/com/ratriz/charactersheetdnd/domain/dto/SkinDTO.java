package com.ratriz.charactersheetdnd.domain.dto;

import java.util.List;

import com.ratriz.charactersheetdnd.domain.entity.Skin;
import com.ratriz.charactersheetdnd.domain.entity.Subrace;

public record SkinDTO(Long key, String name, List<Subrace> subraces, boolean inactive) implements IDTO<Skin> {

	public SkinDTO(Long key) {
		this(key, null, null);
	}

	public SkinDTO(String name, List<Subrace> subraces) {
		this(null, name, subraces);
	}

	public SkinDTO(Long key, String name, List<Subrace> subraces) {
		this(key, name, subraces, false);
	}

	public SkinDTO(Skin entity) {
		this(entity.getKey(), entity.getName(), entity.getSubraces(), entity.isInactive());
	}

	@Override
	public Skin toEntity() {
		return new Skin(this);
	}
}
