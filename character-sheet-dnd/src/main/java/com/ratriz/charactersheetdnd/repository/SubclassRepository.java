package com.ratriz.charactersheetdnd.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ratriz.charactersheetdnd.domain.dto.SubclassDTO;
import com.ratriz.charactersheetdnd.domain.entity.Subclass;
import com.ratriz.charactersheetdnd.infrastructure.ConstantFilter;
import com.ratriz.charactersheetdnd.infrastructure.ConstantPages;

@Repository
public interface SubclassRepository extends JpaRepository<Subclass, Long> {

	default Page<Subclass> findByInactive(boolean inactive) {
		return findByInactive(inactive, ConstantPages.PAGE_REQUEST);
	}

	public Page<Subclass> findByInactive(boolean inactive, Pageable pageRequest);

	default Page<Subclass> find(
			@Param(ConstantFilter.ID_NOT_EQUALS) Long idNe,
			@Param(ConstantFilter.CHARACTER_CLASS_ID_EQUALS) Long characterClassIdEq,
			@Param(ConstantFilter.NAME_LIKE) String nameLk,
			@Param(ConstantFilter.INACTIVE_EQUALS) Boolean inactive) {
		return find(idNe, characterClassIdEq, nameLk, inactive, ConstantPages.PAGE_REQUEST);
	}

	@Query("SELECT DISTINCT SC FROM Subclass SC "
			+ "INNER JOIN SC.characterClass CC "
			+ "WHERE (:" + ConstantFilter.ID_NOT_EQUALS + " is null or SC.id != :" + ConstantFilter.ID_NOT_EQUALS + ")"
			+ "AND (:" + ConstantFilter.CHARACTER_CLASS_ID_EQUALS + " is null or CC.id = :" + ConstantFilter.CHARACTER_CLASS_ID_EQUALS + ")"
			+ "AND (:" + ConstantFilter.NAME_LIKE + " is null or LOWER(SC.name) LIKE %:" + ConstantFilter.NAME_LIKE + "%)"
			+ "AND (:" + ConstantFilter.INACTIVE_EQUALS + " is null or SC.inactive = :" + ConstantFilter.INACTIVE_EQUALS + ")")
	public Page<Subclass> find(
			@Param(ConstantFilter.ID_NOT_EQUALS) Long idNe,
			@Param(ConstantFilter.CHARACTER_CLASS_ID_EQUALS) Long characterClassIdEq,
			@Param(ConstantFilter.NAME_LIKE) String nameLk,
			@Param(ConstantFilter.INACTIVE_EQUALS) Boolean inactiveEq,
			Pageable pageRequest);

	@Query("SELECT COUNT(SC) FROM Subclass SC "
			+ "INNER JOIN SC.characterClass CC "
			+ "WHERE (:" + ConstantFilter.ID_NOT_EQUALS + " is null or SC.id != :" + ConstantFilter.ID_NOT_EQUALS + ")"
			+ "AND (:" + ConstantFilter.CHARACTER_CLASS_ID_EQUALS + " is null or CC.id = :" + ConstantFilter.CHARACTER_CLASS_ID_EQUALS + ")"
			+ "AND (:" + ConstantFilter.NAME_LIKE + " is null or LOWER(SC.name) LIKE %:" + ConstantFilter.NAME_LIKE + "%)"
			+ "AND (:" + ConstantFilter.INACTIVE_EQUALS + " is null or SC.inactive = :" + ConstantFilter.INACTIVE_EQUALS + ")")
	public Long countByFilter(
			@Param(ConstantFilter.ID_NOT_EQUALS) Long idNe,
			@Param(ConstantFilter.CHARACTER_CLASS_ID_EQUALS) Long characterClassIdEq,
			@Param(ConstantFilter.NAME_LIKE) String nameLk,
			@Param(ConstantFilter.INACTIVE_EQUALS) Boolean inactive);
	
	default Page<SubclassDTO> findDto(
			@Param(ConstantFilter.ID_NOT_EQUALS) Long idNe,
			@Param(ConstantFilter.CHARACTER_CLASS_ID_EQUALS) Long characterClassIdEq,
			@Param(ConstantFilter.NAME_LIKE) String nameLk,
			@Param(ConstantFilter.INACTIVE_EQUALS) Boolean inactive) {
		return find(idNe, characterClassIdEq, nameLk, inactive).map(obj -> obj.toDTO());
	}

	default Page<SubclassDTO> findDto(
			@Param(ConstantFilter.ID_NOT_EQUALS) Long idNe,
			@Param(ConstantFilter.CHARACTER_CLASS_ID_EQUALS) Long characterClassIdEq,
			@Param(ConstantFilter.NAME_LIKE) String nameLk,
			@Param(ConstantFilter.INACTIVE_EQUALS) Boolean inactive,
			Pageable pageRequest) {
		return find(idNe, characterClassIdEq, nameLk, inactive, pageRequest).map(obj -> obj.toDTO());
	}

}
