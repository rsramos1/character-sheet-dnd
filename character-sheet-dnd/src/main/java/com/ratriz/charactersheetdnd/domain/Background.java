package com.ratriz.charactersheetdnd.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@Table
@Entity
@Builder
@EqualsAndHashCode(of = "id")
public class Background implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String description;
	private boolean inactive;
	@NonNull
	@Builder.Default
	private LocalDateTime lastUpdate = LocalDateTime.now();
}
