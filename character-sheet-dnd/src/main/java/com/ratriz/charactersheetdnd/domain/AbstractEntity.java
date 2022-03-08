package com.ratriz.charactersheetdnd.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.ColumnDefault;

import com.ratriz.charactersheetdnd.dto.IDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity<T extends IDTO<? extends AbstractEntity<T, K>>, K> implements Serializable {
	private static final long serialVersionUID = 1L;

	@ColumnDefault("CURRENT_TIMESTAMP")
	private LocalDateTime lastUpdate;

	public AbstractEntity() {
		this.lastUpdate = LocalDateTime.now();
	}

	public abstract K getId();

	public abstract T toDTO();
}
