package com.ratriz.charactersheetdnd.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.ratriz.charactersheetdnd.dto.BackgroundDTO;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Background extends AbstractEntity<BackgroundDTO, Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long key;

	@Column(nullable = false)
	private String name;

	@Column(length = 2000)
	private String description;

	@ColumnDefault("false")
	private boolean inactive;

	@NonNull
	@OneToMany(mappedBy = "background")
	private List<Bonds> bonds = List.of();

	@Override
	public Long getId() {
		return key;
	}

	@Override
	public BackgroundDTO toDTO() {
		return new BackgroundDTO(this);
	}
	
	public static Background of(String name, String description) {
		return of(null, name, description, false);
	}

	public static Background of(Long id, String name, String description, boolean inactive) {
		return new Background(id, name, description, inactive, List.of());
	}
	
	public Background changeStatus() {
		setInactive(!inactive);
		return this;
	}

}
