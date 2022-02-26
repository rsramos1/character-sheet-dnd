package com.ratriz.charactersheetdnd.service;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ratriz.charactersheetdnd.dto.BackgroundDTO;
import com.ratriz.charactersheetdnd.repository.BackgroundRepository;
import com.ratriz.charactersheetdnd.util.ParseUtil;

@Service
public class BackgroundService {

	@Autowired
	private BackgroundRepository repository;

	public Page<BackgroundDTO> findAllDto(Pageable pageRequest) {
		return repository.findAllDto(pageRequest);
	}

	public Page<BackgroundDTO> findDto(Map<String, String> params, Pageable pageRequest) {
		return repository.findDto(
				ParseUtil.parseLong(params.get("idEq")),
				ParseUtil.parseLong(params.get("idNe")),
				StringUtils.lowerCase(params.get("nameLk")),
				ParseUtil.parseBoolean(params.get("inactive")),
				pageRequest);
	}
	
	public BackgroundDTO save(BackgroundDTO dto) {
		return new BackgroundDTO(repository.save(dto.toEntity()));
	}
	
	public boolean existsById(Long id) {
		return repository.existsById(id);
	}

}
