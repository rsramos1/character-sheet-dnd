package com.ratriz.charactersheetdnd.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ratriz.charactersheetdnd.dto.BackgroundDTO;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@Table
@Entity
@SuperBuilder
public class Background extends AbstractEntity<Background> {
	private static final long serialVersionUID = 1L;

	@Column(length = 2000)
	private String description;

	@Builder.Default
	@OneToMany(mappedBy = "background")
	private List<Bonds> bonds = new ArrayList<>();

	@Override
	public BackgroundDTO toDTO() {
		return new BackgroundDTO(this);
	}

}
