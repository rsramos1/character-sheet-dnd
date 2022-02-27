package com.ratriz.charactersheetdnd.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@Table
@Entity
@SuperBuilder
public class Bonds extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@ManyToOne(optional = false)
	private Background background;

	@Column(length = 2000)
	private String description;
}
