package com.ratriz.charactersheetdnd.domain.dto;

import java.util.List;

import com.ratriz.charactersheetdnd.domain.entity.Alignment;
import com.ratriz.charactersheetdnd.domain.entity.Flaws;

public record FlawsDTO(Long key, BackgroundDTO background, String name, String description, List<Alignment> alignments,
		boolean inactive) implements IDTO<Flaws> {

	public FlawsDTO(Long key) {
		this(key, null, null, null, null);
	}

	public FlawsDTO(BackgroundDTO background, String name, String description, List<Alignment> alignments) {
		this(null, background, name, description, alignments);
	}

	public FlawsDTO(Long key, BackgroundDTO background, String name, String description, List<Alignment> alignments) {
		this(key, background, name, description, alignments, false);
	}

	public FlawsDTO(Flaws entity) {
		this(entity.getKey(), entity.getBackground().toDTO(), entity.getName(), entity.getDescription(),
				entity.getAlignments(), entity.isInactive());
	}

	@Override
	public Flaws toEntity() {
		return new Flaws(this);
	}
}
