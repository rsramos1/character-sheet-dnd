package com.ratriz.charactersheetdnd.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ratriz.charactersheetdnd.domain.Background;
import com.ratriz.charactersheetdnd.dto.BackgroundDTO;

public interface BackgroundRepository extends JpaRepository<Background, Long> {

	@Query(value = BackgroundDTO.SQL_OBJECT + "ORDER BY b.name ASC")
	public Page<BackgroundDTO> findAllDto(Pageable pageRequest);

	@Query(value = BackgroundDTO.SQL_OBJECT 
			+ "WHERE (:idEq is null or b.id = :idEq)"
			+ "AND (:idNe is null or b.id != :idNe)"
			+ "AND (:nameLk is null or LOWER(b.name) LIKE %:nameLk%)"
			+ "AND (:inactive is null or b.inactive = :inactive)"
			+ "ORDER BY b.name ASC")
	public Page<BackgroundDTO> findDto(
			@Param("idEq") Long idEq,
			@Param("idNe") Long idNe,
			@Param("nameLk") String nameLk,
			@Param("inactive") Boolean inactive,
			Pageable pageRequest);

}
