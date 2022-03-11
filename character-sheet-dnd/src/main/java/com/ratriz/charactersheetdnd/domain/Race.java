package com.ratriz.charactersheetdnd.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.ratriz.charactersheetdnd.dto.RaceDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Race extends AbstractEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long key;

	@NotBlank
	private String name;

	@Length(max = 2000)
	private String description;

	@NotNull
	@Range(min = 0)
	@Column(precision = 3)
	private Integer minHeight;

	@NotNull
	@Range(min = 0)
	@Column(precision = 3)
	private Integer maxHeight;

	@NotNull
	@Range(min = 0)
	@Column(precision = 3, scale = 2)
	private Float minWeight;

	@NotNull
	@Range(min = 0)
	@Column(precision = 3, scale = 2)
	private Float maxWeight;

	@NotNull
	@Range(min = 0)
	@Column(precision = 3)
	private Integer minAge;

	@NotNull
	@Range(min = 0)
	@Column(precision = 3)
	private Integer maxAge;

	@Override
	public Long getId() {
		return key;
	}

	@Override
	public void setId(Long id) {
		this.key = id;
	}

	public Race(RaceDTO dto) {
		super(dto.inactive());
		this.key = dto.key();
		this.name = dto.name();
		this.description = dto.description();
	}

	@Override
	@SuppressWarnings("unchecked")
	public RaceDTO toDTO() {
		return new RaceDTO(this);
	}

}
