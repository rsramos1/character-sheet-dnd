package com.ratriz.charactersheetdnd.domain.dto;

import java.util.List;

import com.ratriz.charactersheetdnd.domain.entity.Alignment;
import com.ratriz.charactersheetdnd.domain.entity.PersonalityTraits;

public record PersonalityTraitsDTO(Long key, BackgroundDTO background, String name, String description,
		List<Alignment> alignments, boolean inactive) implements IDTO<PersonalityTraits> {

	public PersonalityTraitsDTO(Long key) {
		this(key, null, null, null, null);
	}

	public PersonalityTraitsDTO(BackgroundDTO background, String name, String description, List<Alignment> alignments) {
		this(null, background, name, description, alignments);
	}

	public PersonalityTraitsDTO(Long key, BackgroundDTO background, String name, String description,
			List<Alignment> alignments) {
		this(key, background, name, description, alignments, false);
	}

	public PersonalityTraitsDTO(PersonalityTraits entity) {
		this(entity.getKey(), entity.getBackground().toDTO(), entity.getName(), entity.getDescription(),
				entity.getAlignments(), entity.isInactive());
	}

	@Override
	public PersonalityTraits toEntity() {
		return new PersonalityTraits(this);
	}
}
