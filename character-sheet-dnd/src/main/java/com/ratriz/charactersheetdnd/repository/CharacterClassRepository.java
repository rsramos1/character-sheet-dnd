package com.ratriz.charactersheetdnd.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ratriz.charactersheetdnd.domain.CharacterClass;
import com.ratriz.charactersheetdnd.dto.CharacterClassDTO;
import com.ratriz.charactersheetdnd.infrastructure.ConstantFilter;
import com.ratriz.charactersheetdnd.infrastructure.ConstantPages;

@Repository
public interface CharacterClassRepository extends JpaRepository<CharacterClass, Long> {

	default Page<CharacterClass> findByInactive(boolean inactive) {
		return findByInactive(inactive, ConstantPages.PAGE_REQUEST);
	}

	public Page<CharacterClass> findByInactive(boolean inactive, Pageable pageRequest);

	default Page<CharacterClass> find(
			@Param(ConstantFilter.ID_NOT_EQUALS) Long idNe,
			@Param(ConstantFilter.NAME_LIKE) String nameLk,
			@Param(ConstantFilter.INACTIVE_EQUALS) Boolean inactive) {
		return find(idNe, nameLk, inactive, ConstantPages.PAGE_REQUEST);
	}

	@Query("SELECT DISTINCT CC FROM CharacterClass CC "
			+ "WHERE (:" + ConstantFilter.ID_NOT_EQUALS + " is null or CC.id != :" + ConstantFilter.ID_NOT_EQUALS + ")"
			+ "AND (:" + ConstantFilter.NAME_LIKE + " is null or LOWER(CC.name) LIKE %:" + ConstantFilter.NAME_LIKE + "%)"
			+ "AND (:" + ConstantFilter.INACTIVE_EQUALS + " is null or CC.inactive = :" + ConstantFilter.INACTIVE_EQUALS + ")")
	public Page<CharacterClass> find(
			@Param(ConstantFilter.ID_NOT_EQUALS) Long idNe,
			@Param(ConstantFilter.NAME_LIKE) String nameLk,
			@Param(ConstantFilter.INACTIVE_EQUALS) Boolean inactiveEq,
			Pageable pageRequest);

	@Query("SELECT COUNT(CC) FROM CharacterClass CC "
			+ "WHERE (:" + ConstantFilter.ID_NOT_EQUALS + " is null or CC.id != :" + ConstantFilter.ID_NOT_EQUALS + ")"
			+ "AND (:" + ConstantFilter.NAME_LIKE + " is null or LOWER(CC.name) LIKE %:" + ConstantFilter.NAME_LIKE + "%)"
			+ "AND (:" + ConstantFilter.INACTIVE_EQUALS + " is null or CC.inactive = :" + ConstantFilter.INACTIVE_EQUALS + ")")
	public Long countByFilter(
			@Param(ConstantFilter.ID_NOT_EQUALS) Long idNe,
			@Param(ConstantFilter.NAME_LIKE) String nameLk,
			@Param(ConstantFilter.INACTIVE_EQUALS) Boolean inactive);
	
	default Page<CharacterClassDTO> findDto(
			@Param(ConstantFilter.ID_NOT_EQUALS) Long idNe,
			@Param(ConstantFilter.NAME_LIKE) String nameLk,
			@Param(ConstantFilter.INACTIVE_EQUALS) Boolean inactive) {
		return find(idNe, nameLk, inactive).map(obj -> obj.toDTO());
	}

	default Page<CharacterClassDTO> findDto(
			@Param(ConstantFilter.ID_NOT_EQUALS) Long idNe,
			@Param(ConstantFilter.NAME_LIKE) String nameLk,
			@Param(ConstantFilter.INACTIVE_EQUALS) Boolean inactive,
			Pageable pageRequest) {
		return find(idNe, nameLk, inactive, pageRequest).map(obj -> obj.toDTO());
	}

}
