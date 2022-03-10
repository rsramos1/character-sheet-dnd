package com.ratriz.charactersheetdnd.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ratriz.charactersheetdnd.dto.BondsDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Bonds extends AbstractEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long key;

	@ManyToOne(optional = false)
	private Background background;

	@Column(nullable = false)
	private String name;

	@Column(length = 2000)
	private String description;

	@Override
	public Long getId() {
		return key;
	}

	public Bonds(BondsDTO dto) {
		super(dto.inactive());
		this.key = dto.key();
		this.background = dto.background().toEntity();
		this.name = dto.name();
		this.description = dto.description();
	}

	@Override
	@SuppressWarnings("unchecked")
	public BondsDTO toDTO() {
		return new BondsDTO(this);
	}

}
