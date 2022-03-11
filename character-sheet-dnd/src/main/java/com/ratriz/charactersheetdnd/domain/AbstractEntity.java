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
//public abstract class AbstractEntity<T extends IDTO<? extends AbstractEntity<T, K>>, K> implements Serializable {
public abstract class AbstractEntity<K> implements Serializable {
	private static final long serialVersionUID = 1L;

	@ColumnDefault("CURRENT_TIMESTAMP")
	private LocalDateTime lastUpdate;

	@ColumnDefault("false")
	private boolean inactive;

	public AbstractEntity(boolean inactive) {
		this.lastUpdate = LocalDateTime.now();
		this.inactive = inactive;
	}

	public AbstractEntity() {
		this(false);
	}

	public abstract K getId();
	
	public abstract void setId(K id);

	public abstract <U extends IDTO<? extends AbstractEntity<K>>> U toDTO();

	public void changeStatus() {
		setInactive(!inactive);
	}
}
