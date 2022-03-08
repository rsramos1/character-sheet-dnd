package com.ratriz.charactersheetdnd.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ratriz.charactersheetdnd.domain.AbstractEntity;
import com.ratriz.charactersheetdnd.dto.IDTO;
import com.ratriz.charactersheetdnd.exception.ObjectNotFoundException;

public abstract class AbstractService<T extends AbstractEntity<? extends IDTO<T>, K>, K> {

	protected abstract JpaRepository<T, K> getRepository();

	public Page<T> findAll(Pageable pageRequest) {
		return getRepository().findAll(pageRequest);
	}

	@SuppressWarnings("unchecked")
	public <U extends IDTO<T>> Page<U> findAllDto(Pageable pageRequest) {
		return findAll(pageRequest).map(obj -> (U) obj.toDTO());
	}

	@SuppressWarnings("unchecked")
	public <U extends IDTO<T>> U save(IDTO<T> dto) {
		return (U) save(dto.toEntity()).toDTO();
	}

	@SuppressWarnings("unchecked")
	public <U extends IDTO<T>> U save(K id, IDTO<T> dto) {
		if (!getRepository().existsById(id)) {
			throw new ObjectNotFoundException();
		}
		return (U) save(dto.toEntity()).toDTO();
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

}
