package com.ratriz.charactersheetdnd.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ratriz.charactersheetdnd.domain.Bonds;
import com.ratriz.charactersheetdnd.dto.BondsDTO;
import com.ratriz.charactersheetdnd.infrastructure.ConstantFilter;
import com.ratriz.charactersheetdnd.infrastructure.ConstantPages;

@Repository
public interface BondsRepository extends JpaRepository<Bonds, Long> {

	default Page<Bonds> findByInactive(boolean inactive) {
		return findByInactive(inactive, ConstantPages.PAGE_REQUEST);
	}

	public Page<Bonds> findByInactive(boolean inactive, Pageable pageRequest);

	default Page<Bonds> find(
			@Param(ConstantFilter.ID_NOT_EQUALS) Long idNe,
			@Param(ConstantFilter.BACKGROUND_ID_EQUALS) Long backgroundIdEq,
			@Param(ConstantFilter.NAME_LIKE) String nameLk,
			@Param(ConstantFilter.INACTIVE_EQUALS) Boolean inactive) {
		return find(idNe, backgroundIdEq, nameLk, inactive, ConstantPages.PAGE_REQUEST);
	}

	@Query("SELECT DISTINCT BD FROM Bonds BD "
			+ "INNER JOIN BD.background BG "
			+ "WHERE (:" + ConstantFilter.ID_NOT_EQUALS + " is null or BD.id != :" + ConstantFilter.ID_NOT_EQUALS + ")"
			+ "AND (:" + ConstantFilter.BACKGROUND_ID_EQUALS + " is null or BG.id = :" + ConstantFilter.BACKGROUND_ID_EQUALS + ")"
			+ "AND (:" + ConstantFilter.NAME_LIKE + " is null or LOWER(BD.name) LIKE %:" + ConstantFilter.NAME_LIKE + "%)"
			+ "AND (:" + ConstantFilter.INACTIVE_EQUALS + " is null or BD.inactive = :" + ConstantFilter.INACTIVE_EQUALS + ")")
	public Page<Bonds> find(
			@Param(ConstantFilter.ID_NOT_EQUALS) Long idNe,
			@Param(ConstantFilter.BACKGROUND_ID_EQUALS) Long backgroundIdEq,
			@Param(ConstantFilter.NAME_LIKE) String nameLk,
			@Param(ConstantFilter.INACTIVE_EQUALS) Boolean inactiveEq,
			Pageable pageRequest);

	@Query("SELECT COUNT(BD) FROM Bonds BD "
			+ "INNER JOIN BD.background BG "
			+ "WHERE (:" + ConstantFilter.ID_NOT_EQUALS + " is null or BD.id != :" + ConstantFilter.ID_NOT_EQUALS + ")"
			+ "AND (:" + ConstantFilter.BACKGROUND_ID_EQUALS + " is null or BG.id = :" + ConstantFilter.BACKGROUND_ID_EQUALS + ")"
			+ "AND (:" + ConstantFilter.NAME_LIKE + " is null or LOWER(BD.name) LIKE %:" + ConstantFilter.NAME_LIKE + "%)"
			+ "AND (:" + ConstantFilter.INACTIVE_EQUALS + " is null or BD.inactive = :" + ConstantFilter.INACTIVE_EQUALS + ")")
	public Long countByFilter(
			@Param(ConstantFilter.ID_NOT_EQUALS) Long idNe,
			@Param(ConstantFilter.BACKGROUND_ID_EQUALS) Long backgroundIdEq,
			@Param(ConstantFilter.NAME_LIKE) String nameLk,
			@Param(ConstantFilter.INACTIVE_EQUALS) Boolean inactive);
	
	default Page<BondsDTO> findDto(
			@Param(ConstantFilter.ID_NOT_EQUALS) Long idNe,
			@Param(ConstantFilter.BACKGROUND_ID_EQUALS) Long backgroundIdEq,
			@Param(ConstantFilter.NAME_LIKE) String nameLk,
			@Param(ConstantFilter.INACTIVE_EQUALS) Boolean inactive) {
		return find(idNe, backgroundIdEq, nameLk, inactive).map(obj -> obj.toDTO());
	}

	default Page<BondsDTO> findDto(
			@Param(ConstantFilter.ID_NOT_EQUALS) Long idNe,
			@Param(ConstantFilter.BACKGROUND_ID_EQUALS) Long backgroundIdEq,
			@Param(ConstantFilter.NAME_LIKE) String nameLk,
			@Param(ConstantFilter.INACTIVE_EQUALS) Boolean inactive,
			Pageable pageRequest) {
		return find(idNe, backgroundIdEq, nameLk, inactive, pageRequest).map(obj -> obj.toDTO());
	}

}
