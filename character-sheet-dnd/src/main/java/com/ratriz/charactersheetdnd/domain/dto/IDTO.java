package com.ratriz.charactersheetdnd.domain.dto;

import com.ratriz.charactersheetdnd.domain.entity.AbstractEntity;

public interface IDTO<T extends AbstractEntity<?>> {

	public T toEntity();

}
