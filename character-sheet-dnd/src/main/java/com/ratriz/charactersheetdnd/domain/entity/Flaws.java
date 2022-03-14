package com.ratriz.charactersheetdnd.domain.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.ratriz.charactersheetdnd.domain.dto.FlawsDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Flaws extends AbstractEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long key;

	@ManyToOne(optional = false)
	private Background background;

	@NotBlank
	private String name;

	@Length(max = 2000)
	private String description;

	@NotEmpty
	@ManyToMany
	private List<Alignment> alignments = List.of();

	@Override
	public Long getId() {
		return key;
	}

	@Override
	public void setId(Long id) {
		this.key = id;
	}

	public Flaws(FlawsDTO dto) {
		super(dto.inactive());
		this.key = dto.key();
		this.background = dto.background().toEntity();
		this.name = dto.name();
		this.description = dto.description();
	}

	@Override
	@SuppressWarnings("unchecked")
	public FlawsDTO toDTO() {
		return new FlawsDTO(this);
	}

}
