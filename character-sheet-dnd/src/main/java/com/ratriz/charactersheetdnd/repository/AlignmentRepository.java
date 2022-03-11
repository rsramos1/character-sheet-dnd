package com.ratriz.charactersheetdnd.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ratriz.charactersheetdnd.domain.Alignment;
import com.ratriz.charactersheetdnd.dto.AlignmentDTO;
import com.ratriz.charactersheetdnd.infrastructure.ConstantFilter;
import com.ratriz.charactersheetdnd.infrastructure.ConstantPages;

@Repository
public interface AlignmentRepository extends JpaRepository<Alignment, Long> {

	default Page<Alignment> findByInactive(boolean inactive) {
		return findByInactive(inactive, ConstantPages.PAGE_REQUEST);
	}

	public Page<Alignment> findByInactive(boolean inactive, Pageable pageRequest);

	default Page<Alignment> find(
			@Param(ConstantFilter.ID_NOT_EQUALS) Long idNe,
			@Param(ConstantFilter.NAME_LIKE) String nameLk,
			@Param(ConstantFilter.INACTIVE_EQUALS) Boolean inactive) {
		return find(idNe, nameLk, inactive, ConstantPages.PAGE_REQUEST);
	}

	@Query("SELECT DISTINCT AL FROM Alignment AL "
			+ "WHERE (:" + ConstantFilter.ID_NOT_EQUALS + " is null or AL.id != :" + ConstantFilter.ID_NOT_EQUALS + ")"
			+ "AND (:" + ConstantFilter.NAME_LIKE + " is null or LOWER(AL.name) LIKE %:" + ConstantFilter.NAME_LIKE + "%)"
			+ "AND (:" + ConstantFilter.INACTIVE_EQUALS + " is null or AL.inactive = :" + ConstantFilter.INACTIVE_EQUALS + ")")
	public Page<Alignment> find(
			@Param(ConstantFilter.ID_NOT_EQUALS) Long idNe,
			@Param(ConstantFilter.NAME_LIKE) String nameLk,
			@Param(ConstantFilter.INACTIVE_EQUALS) Boolean inactiveEq,
			Pageable pageRequest);

	@Query("SELECT COUNT(AL) FROM Alignment AL "
			+ "WHERE (:" + ConstantFilter.ID_NOT_EQUALS + " is null or AL.id != :" + ConstantFilter.ID_NOT_EQUALS + ")"
			+ "AND (:" + ConstantFilter.NAME_LIKE + " is null or LOWER(AL.name) LIKE %:" + ConstantFilter.NAME_LIKE + "%)"
			+ "AND (:" + ConstantFilter.INACTIVE_EQUALS + " is null or AL.inactive = :" + ConstantFilter.INACTIVE_EQUALS + ")")
	public Long countByFilter(
			@Param(ConstantFilter.ID_NOT_EQUALS) Long idNe,
			@Param(ConstantFilter.NAME_LIKE) String nameLk,
			@Param(ConstantFilter.INACTIVE_EQUALS) Boolean inactive);
	
	default Page<AlignmentDTO> findDto(
			@Param(ConstantFilter.ID_NOT_EQUALS) Long idNe,
			@Param(ConstantFilter.NAME_LIKE) String nameLk,
			@Param(ConstantFilter.INACTIVE_EQUALS) Boolean inactive) {
		return find(idNe, nameLk, inactive).map(obj -> obj.toDTO());
	}

	default Page<AlignmentDTO> findDto(
			@Param(ConstantFilter.ID_NOT_EQUALS) Long idNe,
			@Param(ConstantFilter.NAME_LIKE) String nameLk,
			@Param(ConstantFilter.INACTIVE_EQUALS) Boolean inactive,
			Pageable pageRequest) {
		return find(idNe, nameLk, inactive, pageRequest).map(obj -> obj.toDTO());
	}

}
