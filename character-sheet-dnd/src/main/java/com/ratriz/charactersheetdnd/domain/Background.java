package com.ratriz.charactersheetdnd.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ratriz.charactersheetdnd.dto.BackgroundDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Background extends AbstractEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long key;

	@Column(nullable = false)
	private String name;

	@Column(length = 2000)
	private String description;

	@Override
	public Long getId() {
		return key;
	}

	public Background(BackgroundDTO dto) {
		super(dto.inactive());
		this.key = dto.key();
		this.name = dto.name();
		this.description = dto.description();
	}

	@Override
	@SuppressWarnings("unchecked")
	public BackgroundDTO toDTO() {
		return new BackgroundDTO(this);
	}

}
