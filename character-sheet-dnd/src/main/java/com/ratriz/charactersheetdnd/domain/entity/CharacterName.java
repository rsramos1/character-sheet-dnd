package com.ratriz.charactersheetdnd.domain.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.ratriz.charactersheetdnd.domain.dto.CharacterNameDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CharacterName extends AbstractEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long key;

	@NotBlank
	private String name;

	@NotNull
	private Character gender;

	@NotEmpty
	@ManyToMany
	private List<Subrace> subraces = List.of();

	@Override
	public Long getId() {
		return key;
	}

	@Override
	public void setId(Long id) {
		this.key = id;
	}

	public CharacterName(CharacterNameDTO dto) {
		super(dto.inactive());
		this.key = dto.key();
		this.name = dto.name();
		this.gender = dto.gender();
	}

	@Override
	@SuppressWarnings("unchecked")
	public CharacterNameDTO toDTO() {
		return new CharacterNameDTO(this);
	}

}
