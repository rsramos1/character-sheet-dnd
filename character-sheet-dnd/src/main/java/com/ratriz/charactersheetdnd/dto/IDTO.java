package com.ratriz.charactersheetdnd.dto;

import com.ratriz.charactersheetdnd.domain.AbstractEntity;

public interface IDTO<T extends AbstractEntity<?>> {

	public T toEntity();
	
}
