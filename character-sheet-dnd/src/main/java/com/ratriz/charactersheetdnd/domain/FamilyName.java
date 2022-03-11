package com.ratriz.charactersheetdnd.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.ratriz.charactersheetdnd.dto.FamilyNameDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FamilyName extends AbstractEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long key;

	@NotBlank
	private String name;

	@Override
	public Long getId() {
		return key;
	}

	@Override
	public void setId(Long id) {
		this.key = id;
	}

	public FamilyName(FamilyNameDTO dto) {
		super(dto.inactive());
		this.key = dto.key();
		this.name = dto.name();
	}

	@Override
	@SuppressWarnings("unchecked")
	public FamilyNameDTO toDTO() {
		return new FamilyNameDTO(this);
	}

}