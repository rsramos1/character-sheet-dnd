package com.ratriz.charactersheetdnd.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ratriz.charactersheetdnd.domain.dto.BackgroundTypeDTO;
import com.ratriz.charactersheetdnd.domain.entity.BackgroundType;
import com.ratriz.charactersheetdnd.infrastructure.ConstantFilter;
import com.ratriz.charactersheetdnd.infrastructure.ConstantPages;

@Repository
public interface BackgroundTypeRepository extends JpaRepository<BackgroundType, Long> {

	default Page<BackgroundType> findByInactive(boolean inactive) {
		return findByInactive(inactive, ConstantPages.PAGE_REQUEST);
	}

	public Page<BackgroundType> findByInactive(boolean inactive, Pageable pageRequest);

	default Page<BackgroundType> find(
			@Param(ConstantFilter.ID_NOT_EQUALS) Long idNe,
			@Param(ConstantFilter.BACKGROUND_ID_EQUALS) Long backgroundIdEq,
			@Param(ConstantFilter.NAME_LIKE) String nameLk,
			@Param(ConstantFilter.INACTIVE_EQUALS) Boolean inactive) {
		return find(idNe, backgroundIdEq, nameLk, inactive, ConstantPages.PAGE_REQUEST);
	}

	@Query("SELECT DISTINCT BT FROM BackgroundType BT "
			+ "INNER JOIN BT.background BG "
			+ "WHERE (:" + ConstantFilter.ID_NOT_EQUALS + " is null or BT.id != :" + ConstantFilter.ID_NOT_EQUALS + ")"
			+ "AND (:" + ConstantFilter.BACKGROUND_ID_EQUALS + " is null or BG.id = :" + ConstantFilter.BACKGROUND_ID_EQUALS + ")"
			+ "AND (:" + ConstantFilter.NAME_LIKE + " is null or LOWER(BT.name) LIKE %:" + ConstantFilter.NAME_LIKE + "%)"
			+ "AND (:" + ConstantFilter.INACTIVE_EQUALS + " is null or BT.inactive = :" + ConstantFilter.INACTIVE_EQUALS + ")")
	public Page<BackgroundType> find(
			@Param(ConstantFilter.ID_NOT_EQUALS) Long idNe,
			@Param(ConstantFilter.BACKGROUND_ID_EQUALS) Long backgroundIdEq,
			@Param(ConstantFilter.NAME_LIKE) String nameLk,
			@Param(ConstantFilter.INACTIVE_EQUALS) Boolean inactiveEq,
			Pageable pageRequest);

	@Query("SELECT COUNT(BT) FROM BackgroundType BT "
			+ "INNER JOIN BT.background BG "
			+ "WHERE (:" + ConstantFilter.ID_NOT_EQUALS + " is null or BT.id != :" + ConstantFilter.ID_NOT_EQUALS + ")"
			+ "AND (:" + ConstantFilter.BACKGROUND_ID_EQUALS + " is null or BG.id = :" + ConstantFilter.BACKGROUND_ID_EQUALS + ")"
			+ "AND (:" + ConstantFilter.NAME_LIKE + " is null or LOWER(BT.name) LIKE %:" + ConstantFilter.NAME_LIKE + "%)"
			+ "AND (:" + ConstantFilter.INACTIVE_EQUALS + " is null or BT.inactive = :" + ConstantFilter.INACTIVE_EQUALS + ")")
	public Long countByFilter(
			@Param(ConstantFilter.ID_NOT_EQUALS) Long idNe,
			@Param(ConstantFilter.BACKGROUND_ID_EQUALS) Long backgroundIdEq,
			@Param(ConstantFilter.NAME_LIKE) String nameLk,
			@Param(ConstantFilter.INACTIVE_EQUALS) Boolean inactive);
	
	default Page<BackgroundTypeDTO> findDto(
			@Param(ConstantFilter.ID_NOT_EQUALS) Long idNe,
			@Param(ConstantFilter.BACKGROUND_ID_EQUALS) Long backgroundIdEq,
			@Param(ConstantFilter.NAME_LIKE) String nameLk,
			@Param(ConstantFilter.INACTIVE_EQUALS) Boolean inactive) {
		return find(idNe, backgroundIdEq, nameLk, inactive).map(obj -> obj.toDTO());
	}

	default Page<BackgroundTypeDTO> findDto(
			@Param(ConstantFilter.ID_NOT_EQUALS) Long idNe,
			@Param(ConstantFilter.BACKGROUND_ID_EQUALS) Long backgroundIdEq,
			@Param(ConstantFilter.NAME_LIKE) String nameLk,
			@Param(ConstantFilter.INACTIVE_EQUALS) Boolean inactive,
			Pageable pageRequest) {
		return find(idNe, backgroundIdEq, nameLk, inactive, pageRequest).map(obj -> obj.toDTO());
	}

}
