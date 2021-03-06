package com.ratriz.charactersheetdnd.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.ratriz.charactersheetdnd.domain.dto.CharacterClassDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CharacterClass extends AbstractEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long key;

	@NotBlank
	private String name;

	@Length(max = 2000)
	private String description;

	@Override
	public Long getId() {
		return key;
	}

	@Override
	public void setId(Long id) {
		this.key = id;
	}

	public CharacterClass(CharacterClassDTO dto) {
		super(dto.inactive());
		this.key = dto.key();
		this.name = dto.name();
		this.description = dto.description();
	}

	@Override
	@SuppressWarnings("unchecked")
	public CharacterClassDTO toDTO() {
		return new CharacterClassDTO(this);
	}

}
