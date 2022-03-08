package com.ratriz.charactersheetdnd.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ratriz.charactersheetdnd.domain.Background;
import com.ratriz.charactersheetdnd.dto.BackgroundDTO;
import com.ratriz.charactersheetdnd.exception.ObjectNotFoundException;
import com.ratriz.charactersheetdnd.repository.BackgroundRepository;
import com.ratriz.charactersheetdnd.util.ParseUtil;

@Service
public class BackgroundService extends AbstractService<Background, Long> {

	@Autowired
	private BackgroundRepository repository;

	@Override
	protected JpaRepository<Background, Long> getRepository() {
		return this.repository;
	}

	public Page<BackgroundDTO> findDto(Map<String, String> params, Pageable pageRequest) {
		return repository.findDto(
				ParseUtil.parseLong(params.get("idNe")),
				params.get("nameLk"),
				ParseUtil.parseBoolean(params.get("inactive")),
				pageRequest);
	}
	
	public BackgroundDTO changeStatus(Long id) {
		Background background = findById(id);
		background.setInactive(!background.isInactive());
		return repository.save(background).toDTO();
	}

}
