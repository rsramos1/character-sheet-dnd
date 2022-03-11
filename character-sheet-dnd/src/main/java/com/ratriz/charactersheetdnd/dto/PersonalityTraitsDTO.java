package com.ratriz.charactersheetdnd.dto;

import com.ratriz.charactersheetdnd.domain.PersonalityTraits;

public record PersonalityTraitsDTO(Long key, BackgroundDTO background, String name, String description,
		boolean inactive) implements IDTO<PersonalityTraits> {

	public PersonalityTraitsDTO(Long key) {
		this(null, null, null);
	}

	public PersonalityTraitsDTO(BackgroundDTO background, String name, String description) {
		this(null, background, name, description);
	}

	public PersonalityTraitsDTO(Long key, BackgroundDTO background, String name, String description) {
		this(key, background, name, description, false);
	}

	public PersonalityTraitsDTO(PersonalityTraits entity) {
		this(entity.getKey(), entity.getBackground().toDTO(), entity.getName(), entity.getDescription(),
				entity.isInactive());
	}

	@Override
	public PersonalityTraits toEntity() {
		return new PersonalityTraits(this);
	}
}
