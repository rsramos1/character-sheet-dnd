package com.ratriz.charactersheetdnd.domain.dto;

import java.util.List;

import com.ratriz.charactersheetdnd.domain.entity.Alignment;
import com.ratriz.charactersheetdnd.domain.entity.Ideals;

public record IdealsDTO(Long key, BackgroundDTO background, String name, String description, List<Alignment> alignments,
		boolean inactive) implements IDTO<Ideals> {

	public IdealsDTO(Long key) {
		this(key, null, null, null, null);
	}

	public IdealsDTO(BackgroundDTO background, String name, String description, List<Alignment> alignments) {
		this(null, background, name, description, alignments);
	}

	public IdealsDTO(Long key, BackgroundDTO background, String name, String description, List<Alignment> alignments) {
		this(key, background, name, description, alignments, false);
	}

	public IdealsDTO(Ideals entity) {
		this(entity.getKey(), entity.getBackground().toDTO(), entity.getName(), entity.getDescription(),
				entity.getAlignments(), entity.isInactive());
	}

	@Override
	public Ideals toEntity() {
		return new Ideals(this);
	}
}
