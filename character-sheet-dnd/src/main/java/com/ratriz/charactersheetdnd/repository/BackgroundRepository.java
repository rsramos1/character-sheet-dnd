package com.ratriz.charactersheetdnd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ratriz.charactersheetdnd.domain.Background;
import com.ratriz.charactersheetdnd.dto.BackgroundDTO;

public interface BackgroundRepository extends JpaRepository<Background, Long> {

	@Query(value = BackgroundDTO.SQL_OBJECT + "ORDER BY b.name ASC")
	public List<BackgroundDTO> findAllDto();

}
