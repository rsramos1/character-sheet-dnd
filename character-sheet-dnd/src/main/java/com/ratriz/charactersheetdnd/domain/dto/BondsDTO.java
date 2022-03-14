package com.ratriz.charactersheetdnd.domain.dto;

import java.util.List;

import com.ratriz.charactersheetdnd.domain.entity.Alignment;
import com.ratriz.charactersheetdnd.domain.entity.Bonds;

public record BondsDTO(Long key, BackgroundDTO background, String name, String description, List<Alignment> alignments,
		boolean inactive) implements IDTO<Bonds> {

	public BondsDTO(Long key) {
		this(key, null, null, null, null);
	}

	public BondsDTO(BackgroundDTO background, String name, String description, List<Alignment> alignments) {
		this(null, background, name, description, alignments);
	}

	public BondsDTO(Long key, BackgroundDTO background, String name, String description, List<Alignment> alignments) {
		this(key, background, name, description, alignments, false);
	}

	public BondsDTO(Bonds entity) {
		this(entity.getKey(), entity.getBackground().toDTO(), entity.getName(), entity.getDescription(),
				entity.getAlignments(), entity.isInactive());
	}

	@Override
	public Bonds toEntity() {
		return new Bonds(this);
	}
}
