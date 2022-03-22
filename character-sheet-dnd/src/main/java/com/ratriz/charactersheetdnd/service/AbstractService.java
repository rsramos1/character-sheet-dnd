package com.ratriz.charactersheetdnd.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ratriz.charactersheetdnd.domain.dto.IDTO;
import com.ratriz.charactersheetdnd.domain.entity.AbstractEntity;
import com.ratriz.charactersheetdnd.exception.ObjectNotFoundException;
import com.ratriz.charactersheetdnd.infrastructure.ConstantPages;

public abstract class AbstractService<T extends AbstractEntity<K>, K> {

	protected abstract JpaRepository<T, K> getRepository();

	public abstract Page<? extends IDTO<T>> findDto(Map<String, String> params, Pageable pageRequest);

	public abstract IDTO<T> findOneRandom(Map<String, String> params);

	public Page<T> findAll(Pageable pageRequest) {
		return getRepository().findAll(pageRequest);
	}

	@SuppressWarnings("unchecked")
	public <U extends IDTO<T>> Page<U> findAllDto(Pageable pageRequest) {
		return findAll(pageRequest).map(obj -> (U) obj.toDTO());
	}

	public Page<? extends IDTO<T>> findDto(Map<String, String> params) {
		return findDto(params, ConstantPages.PAGE_REQUEST);
	}

	@SuppressWarnings("unchecked")
	public <U extends IDTO<T>> U insert(IDTO<T> dto) {
		return (U) save(dto.toEntity()).toDTO();
	}

	@SuppressWarnings("unchecked")
	public <U extends IDTO<T>> U update(K id, IDTO<T> dto) {
		if (!existsById(id)) {
			throw new ObjectNotFoundException();
		}
		return (U) save(Optional.of(dto.toEntity()).map(entity -> {
			entity.setId(id);
			return entity;
		}).get()).toDTO();
	}

	public T save(T entity) {
		return getRepository().save(entity);
	}

	public T delete(K id) {
		T entity = findById(id);
		getRepository().delete(entity);
		return entity;
	}

	public boolean existsById(K id) {
		return getRepository().existsById(id);
	}

	public T findById(K id) {
		return getRepository().findById(id).orElseThrow(() -> new ObjectNotFoundException());
	}

	@SuppressWarnings("unchecked")
	public <U extends IDTO<T>> U findByIdDto(K id) {
		return (U) findById(id).toDTO();
	}

	public IDTO<T> changeStatus(K id) {
		return getRepository().save(Optional.of(findById(id)).map(obj -> {
			obj.changeStatus();
			return obj;
		}).get()).toDTO();
	}

}
