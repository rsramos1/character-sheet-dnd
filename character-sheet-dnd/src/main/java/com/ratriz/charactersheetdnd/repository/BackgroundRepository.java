package com.ratriz.charactersheetdnd.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ratriz.charactersheetdnd.domain.Background;
import com.ratriz.charactersheetdnd.dto.BackgroundDTO;
import com.ratriz.charactersheetdnd.infrastructure.ConstantPages;

@Repository
public interface BackgroundRepository extends JpaRepository<Background, Long> {

	public default Page<Background> findByInactive(boolean inactive) {
		return findByInactive(inactive, ConstantPages.PAGE_REQUEST);
	}

	public Page<Background> findByInactive(boolean inactive, Pageable pageRequest);

	public default Page<Background> find(
			@Param("idNe") Long idNe,
			@Param("nameLk") String nameLk,
			@Param("inactive") Boolean inactive) {
		return find(idNe, nameLk, inactive, ConstantPages.PAGE_REQUEST);
	}

	@Query("SELECT DISTINCT BG FROM Background BG "
			+ "WHERE (:idNe is null or BG.id != :idNe)"
			+ "AND (:nameLk is null or LOWER(BG.name) LIKE %:nameLk%)"
			+ "AND (:inactive is null or BG.inactive = :inactive)")
	public Page<Background> find(
			@Param("idNe") Long idNe,
			@Param("nameLk") String nameLk,
			@Param("inactive") Boolean inactive,
			Pageable pageRequest);
	
	public default Page<BackgroundDTO> findDto(
			@Param("idNe") Long idNe,
			@Param("nameLk") String nameLk,
			@Param("inactive") Boolean inactive) {
		return find(idNe, nameLk, inactive).map(obj -> obj.toDTO());
	}

	public default Page<BackgroundDTO> findDto(
			@Param("idNe") Long idNe,
			@Param("nameLk") String nameLk,
			@Param("inactive") Boolean inactive,
			Pageable pageRequest) {
		return find(idNe, nameLk, inactive, pageRequest).map(obj -> obj.toDTO());
	}

}
