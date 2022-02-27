package com.ratriz.charactersheetdnd.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.ColumnDefault;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@SuperBuilder
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	private String name;
	@ColumnDefault("false")
	private boolean inactive;
	@ColumnDefault("CURRENT_TIMESTAMP")
	private LocalDateTime lastUpdate;
}
