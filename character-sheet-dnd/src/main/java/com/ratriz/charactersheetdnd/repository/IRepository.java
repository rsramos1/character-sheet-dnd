package com.ratriz.charactersheetdnd.repository;

import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ratriz.charactersheetdnd.domain.AbstractEntity;
import com.ratriz.charactersheetdnd.dto.IDTO;
import com.ratriz.charactersheetdnd.infrastructure.ConstantPages;
import com.ratriz.charactersheetdnd.util.ParseUtil;

public interface IRepository<T extends AbstractEntity<T>, K> extends JpaRepository<T, K> {

	public default IDTO<T> findByIdDto(K id) {
		Optional<T> entity = findById(id);
		return entity.isPresent() ? entity.get().toDTO() : null;
	}

	public default Page<IDTO<T>> findAllDto(Pageable pageRequest) {
		return findAll(pageRequest).map(obj -> (IDTO<T>) obj.toDTO());
	}

	public default Page<IDTO<T>> findDto(Map<String, String> params) {
		return find(params).map(obj -> (IDTO<T>) obj.toDTO());
	}

	public default Page<IDTO<T>> findDto(Map<String, String> params, Pageable pageRequest) {
		return find(params, pageRequest).map(obj -> (IDTO<T>) obj.toDTO());
	}

	public default Page<T> find(Map<String, String> params) {
		return find(params, ConstantPages.PAGE_REQUEST);
	}

	public default Page<T> find(Map<String, String> params, Pageable pageRequest) {
		Long idEq = ParseUtil.parseLong(params.get("idEq"));
		Long idNe = ParseUtil.parseLong(params.get("idNe"));
		String nameLk = params.get("nameLk");
		Boolean inactive = ParseUtil.parseBoolean(params.get("inactive"));

		if (ObjectUtils.allNotNull(idEq, nameLk, inactive)) {
			return findByIdIsAndNameContainingIgnoreCaseAndInactiveIs(idEq, nameLk, inactive, pageRequest);
		} else if (ObjectUtils.allNotNull(idEq, nameLk)) {
			return findByIdIsAndNameContainingIgnoreCase(idEq, nameLk, pageRequest);
		} else if (ObjectUtils.allNotNull(idEq, inactive)) {
			return findByIdIsAndInactiveIs(idEq, inactive, pageRequest);
		} else if (ObjectUtils.allNotNull(idEq)) {
			return findByIdIs(idEq, pageRequest);
		} else if (ObjectUtils.allNotNull(idNe, nameLk, inactive)) {
			return findByIdNotAndNameContainingIgnoreCaseAndInactiveIs(idNe, nameLk, inactive, pageRequest);
		} else if (ObjectUtils.allNotNull(idNe, nameLk)) {
			return findByIdNotAndNameContainingIgnoreCase(idNe, nameLk, pageRequest);
		} else if (ObjectUtils.allNotNull(idNe, inactive)) {
			return findByIdNotAndInactiveIs(idNe, inactive, pageRequest);
		} else if (ObjectUtils.allNotNull(idNe)) {
			return findByIdNot(idNe, pageRequest);
		} else if (ObjectUtils.allNotNull(nameLk, inactive)) {
			return findByNameContainingIgnoreCaseAndInactiveIs(nameLk, inactive, pageRequest);
		} else if (ObjectUtils.allNotNull(nameLk, pageRequest)) {
			return findByNameContainingIgnoreCase(nameLk, pageRequest);
		} else if (ObjectUtils.allNotNull(inactive)) {
			return findByInactive(inactive, pageRequest);
		}

		return findAll(pageRequest);
	}

	public Page<T> findByIdIs(Long id, Pageable pageRequest);

	public Page<T> findByIdNot(Long id, Pageable pageRequest);

	public Page<T> findByNameContainingIgnoreCase(String name, Pageable pageRequest);

	public Page<T> findByInactive(boolean inactive, Pageable pageRequest);

	public Page<T> findByIdIsAndNameContainingIgnoreCase(Long id, String name, Pageable pageRequest);

	public Page<T> findByIdNotAndNameContainingIgnoreCase(Long id, String name, Pageable pageRequest);

	public Page<T> findByIdIsAndInactiveIs(Long id, boolean inactive, Pageable pageRequest);

	public Page<T> findByIdNotAndInactiveIs(Long id, boolean inactive, Pageable pageRequest);

	public Page<T> findByNameContainingIgnoreCaseAndInactiveIs(String name, boolean inactive, Pageable pageRequest);

	public Page<T> findByIdIsAndNameContainingIgnoreCaseAndInactiveIs(Long id, String name, boolean inactive,
			Pageable pageRequest);

	public Page<T> findByIdNotAndNameContainingIgnoreCaseAndInactiveIs(Long id, String name, boolean inactive,
			Pageable pageRequest);

}
