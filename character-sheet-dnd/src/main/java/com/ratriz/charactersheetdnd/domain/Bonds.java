package com.ratriz.charactersheetdnd.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.ratriz.charactersheetdnd.dto.BondsDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Bonds extends AbstractEntity<BondsDTO, Long> {
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

	@ColumnDefault("false")
	private boolean inactive;

	@Override
	public Long getId() {
		return key;
	}

	@Override
	public BondsDTO toDTO() {
		return new BondsDTO(this);
	}

	public static Bonds of(String name, Background background, String description) {
		return of(null, background, name, description, false);
	}

	public static Bonds of(Long id, Background background, String name, String description, boolean inactive) {
		return new Bonds(id, background, name, description, inactive);
	}
}
