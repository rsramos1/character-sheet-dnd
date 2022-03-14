package com.ratriz.charactersheetdnd.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ratriz.charactersheetdnd.domain.dto.CharacterNameDTO;
import com.ratriz.charactersheetdnd.domain.entity.CharacterName;
import com.ratriz.charactersheetdnd.infrastructure.ConstantFilter;
import com.ratriz.charactersheetdnd.infrastructure.ConstantPages;

@Repository
public interface CharacterNameRepository extends JpaRepository<CharacterName, Long> {

	default Page<CharacterName> findByInactive(boolean inactive) {
		return findByInactive(inactive, ConstantPages.PAGE_REQUEST);
	}

	public Page<CharacterName> findByInactive(boolean inactive, Pageable pageRequest);

	default Page<CharacterName> find(
			@Param(ConstantFilter.ID_NOT_EQUALS) Long idNe,
			@Param(ConstantFilter.NAME_LIKE) String nameLk,
			@Param(ConstantFilter.GENDER_EQUALS) Character genderEq,
			@Param(ConstantFilter.INACTIVE_EQUALS) Boolean inactive) {
		return find(idNe, nameLk, genderEq, inactive, ConstantPages.PAGE_REQUEST);
	}

	@Query("SELECT DISTINCT CN FROM CharacterName CN "
			+ "WHERE (:" + ConstantFilter.ID_NOT_EQUALS + " is null or CN.id != :" + ConstantFilter.ID_NOT_EQUALS + ")"
			+ "AND (:" + ConstantFilter.NAME_LIKE + " is null or LOWER(CN.name) LIKE %:" + ConstantFilter.NAME_LIKE + "%)"
			+ "AND (:" + ConstantFilter.GENDER_EQUALS + " is null or CN.gender = :" + ConstantFilter.GENDER_EQUALS + ")"
			+ "AND (:" + ConstantFilter.INACTIVE_EQUALS + " is null or CN.inactive = :" + ConstantFilter.INACTIVE_EQUALS + ")")
	public Page<CharacterName> find(
			@Param(ConstantFilter.ID_NOT_EQUALS) Long idNe,
			@Param(ConstantFilter.NAME_LIKE) String nameLk,
			@Param(ConstantFilter.GENDER_EQUALS) Character genderEq,
			@Param(ConstantFilter.INACTIVE_EQUALS) Boolean inactiveEq,
			Pageable pageRequest);

	@Query("SELECT COUNT(CN) FROM CharacterName CN "
			+ "WHERE (:" + ConstantFilter.ID_NOT_EQUALS + " is null or CN.id != :" + ConstantFilter.ID_NOT_EQUALS + ")"
			+ "AND (:" + ConstantFilter.NAME_LIKE + " is null or LOWER(CN.name) LIKE %:" + ConstantFilter.NAME_LIKE + "%)"
			+ "AND (:" + ConstantFilter.GENDER_EQUALS + " is null or CN.gender = :" + ConstantFilter.GENDER_EQUALS + ")"
			+ "AND (:" + ConstantFilter.INACTIVE_EQUALS + " is null or CN.inactive = :" + ConstantFilter.INACTIVE_EQUALS + ")")
	public Long countByFilter(
			@Param(ConstantFilter.ID_NOT_EQUALS) Long idNe,
			@Param(ConstantFilter.NAME_LIKE) String nameLk,
			@Param(ConstantFilter.GENDER_EQUALS) Character genderEq,
			@Param(ConstantFilter.INACTIVE_EQUALS) Boolean inactive);
	
	default Page<CharacterNameDTO> findDto(
			@Param(ConstantFilter.ID_NOT_EQUALS) Long idNe,
			@Param(ConstantFilter.NAME_LIKE) String nameLk,
			@Param(ConstantFilter.GENDER_EQUALS) Character genderEq,
			@Param(ConstantFilter.INACTIVE_EQUALS) Boolean inactive) {
		return find(idNe, nameLk, genderEq, inactive).map(obj -> obj.toDTO());
	}

	default Page<CharacterNameDTO> findDto(
			@Param(ConstantFilter.ID_NOT_EQUALS) Long idNe,
			@Param(ConstantFilter.NAME_LIKE) String nameLk,
			@Param(ConstantFilter.GENDER_EQUALS) Character genderEq,
			@Param(ConstantFilter.INACTIVE_EQUALS) Boolean inactive,
			Pageable pageRequest) {
		return find(idNe, nameLk, genderEq, inactive, pageRequest).map(obj -> obj.toDTO());
	}

}
