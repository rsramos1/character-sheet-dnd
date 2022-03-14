package com.ratriz.charactersheetdnd.domain.dto;

import java.util.List;

import com.ratriz.charactersheetdnd.domain.entity.Hair;
import com.ratriz.charactersheetdnd.domain.entity.Subrace;

public record HairDTO(Long key, String name, List<Subrace> subraces, boolean inactive) implements IDTO<Hair> {

	public HairDTO(Long key) {
		this(key, null, null);
	}

	public HairDTO(String name, List<Subrace> subraces) {
		this(null, name, subraces);
	}

	public HairDTO(Long key, String name, List<Subrace> subraces) {
		this(key, name, subraces, false);
	}

	public HairDTO(Hair entity) {
		this(entity.getKey(), entity.getName(), entity.getSubraces(), entity.isInactive());
	}

	@Override
	public Hair toEntity() {
		return new Hair(this);
	}
}
