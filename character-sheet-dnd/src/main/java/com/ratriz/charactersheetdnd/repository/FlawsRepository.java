package com.ratriz.charactersheetdnd.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ratriz.charactersheetdnd.domain.Flaws;
import com.ratriz.charactersheetdnd.dto.FlawsDTO;
import com.ratriz.charactersheetdnd.infrastructure.ConstantFilter;
import com.ratriz.charactersheetdnd.infrastructure.ConstantPages;

@Repository
public interface FlawsRepository extends JpaRepository<Flaws, Long> {

	default Page<Flaws> findByInactive(boolean inactive) {
		return findByInactive(inactive, ConstantPages.PAGE_REQUEST);
	}

	public Page<Flaws> findByInactive(boolean inactive, Pageable pageRequest);

	default Page<Flaws> find(
			@Param(ConstantFilter.ID_NOT_EQUALS) Long idNe,
			@Param(ConstantFilter.BACKGROUND_ID_EQUALS) Long backgroundIdEq,
			@Param(ConstantFilter.NAME_LIKE) String nameLk,
			@Param(ConstantFilter.INACTIVE_EQUALS) Boolean inactive) {
		return find(idNe, backgroundIdEq, nameLk, inactive, ConstantPages.PAGE_REQUEST);
	}

	@Query("SELECT DISTINCT FW FROM Flaws FW "
			+ "INNER JOIN FW.background BG "
			+ "WHERE (:" + ConstantFilter.ID_NOT_EQUALS + " is null or FW.id != :" + ConstantFilter.ID_NOT_EQUALS + ")"
			+ "AND (:" + ConstantFilter.BACKGROUND_ID_EQUALS + " is null or BG.id = :" + ConstantFilter.BACKGROUND_ID_EQUALS + ")"
			+ "AND (:" + ConstantFilter.NAME_LIKE + " is null or LOWER(FW.name) LIKE %:" + ConstantFilter.NAME_LIKE + "%)"
			+ "AND (:" + ConstantFilter.INACTIVE_EQUALS + " is null or FW.inactive = :" + ConstantFilter.INACTIVE_EQUALS + ")")
	public Page<Flaws> find(
			@Param(ConstantFilter.ID_NOT_EQUALS) Long idNe,
			@Param(ConstantFilter.BACKGROUND_ID_EQUALS) Long backgroundIdEq,
			@Param(ConstantFilter.NAME_LIKE) String nameLk,
			@Param(ConstantFilter.INACTIVE_EQUALS) Boolean inactiveEq,
			Pageable pageRequest);

	@Query("SELECT COUNT(FW) FROM Flaws FW "
			+ "INNER JOIN FW.background BG "
			+ "WHERE (:" + ConstantFilter.ID_NOT_EQUALS + " is null or FW.id != :" + ConstantFilter.ID_NOT_EQUALS + ")"
			+ "AND (:" + ConstantFilter.BACKGROUND_ID_EQUALS + " is null or BG.id = :" + ConstantFilter.BACKGROUND_ID_EQUALS + ")"
			+ "AND (:" + ConstantFilter.NAME_LIKE + " is null or LOWER(FW.name) LIKE %:" + ConstantFilter.NAME_LIKE + "%)"
			+ "AND (:" + ConstantFilter.INACTIVE_EQUALS + " is null or FW.inactive = :" + ConstantFilter.INACTIVE_EQUALS + ")")
	public Long countByFilter(
			@Param(ConstantFilter.ID_NOT_EQUALS) Long idNe,
			@Param(ConstantFilter.BACKGROUND_ID_EQUALS) Long backgroundIdEq,
			@Param(ConstantFilter.NAME_LIKE) String nameLk,
			@Param(ConstantFilter.INACTIVE_EQUALS) Boolean inactive);
	
	default Page<FlawsDTO> findDto(
			@Param(ConstantFilter.ID_NOT_EQUALS) Long idNe,
			@Param(ConstantFilter.BACKGROUND_ID_EQUALS) Long backgroundIdEq,
			@Param(ConstantFilter.NAME_LIKE) String nameLk,
			@Param(ConstantFilter.INACTIVE_EQUALS) Boolean inactive) {
		return find(idNe, backgroundIdEq, nameLk, inactive).map(obj -> obj.toDTO());
	}

	default Page<FlawsDTO> findDto(
			@Param(ConstantFilter.ID_NOT_EQUALS) Long idNe,
			@Param(ConstantFilter.BACKGROUND_ID_EQUALS) Long backgroundIdEq,
			@Param(ConstantFilter.NAME_LIKE) String nameLk,
			@Param(ConstantFilter.INACTIVE_EQUALS) Boolean inactive,
			Pageable pageRequest) {
		return find(idNe, backgroundIdEq, nameLk, inactive, pageRequest).map(obj -> obj.toDTO());
	}

}
