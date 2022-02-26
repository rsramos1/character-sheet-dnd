package com.ratriz.charactersheetdnd.dto;

import com.ratriz.charactersheetdnd.domain.Background;

public record BackgroundDTO(Long id, String name, String description, boolean inactive) implements IDTO<Background> {

	public static final String SQL_OBJECT = "SELECT new com.ratriz.charactersheetdnd.dto.BackgroundDTO(b.id, b.name, b.description, b.inactive) FROM Background b ";

	public BackgroundDTO(Background entity) {
		this(entity.getId(), entity.getName(), entity.getDescription(), entity.isInactive());
	}

	@Override
	public Background toEntity() {
		return Background.builder().id(id).name(name).description(description).inactive(inactive).build();
	}
}
