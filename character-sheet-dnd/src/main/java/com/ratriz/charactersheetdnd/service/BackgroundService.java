package com.ratriz.charactersheetdnd.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ratriz.charactersheetdnd.dto.BackgroundDTO;
import com.ratriz.charactersheetdnd.repository.BackgroundRepository;

@Service
public class BackgroundService {

	@Autowired
	private BackgroundRepository repository;

	public Page<BackgroundDTO> findAllDto(Pageable pageRequest) {
		return repository.findAllDto(pageRequest).map(obj -> (BackgroundDTO) obj);
	}

	public Page<BackgroundDTO> findDto(Map<String, String> params, Pageable pageRequest) {
		return repository.findDto(params, pageRequest).map(obj -> (BackgroundDTO) obj);
	}

	public BackgroundDTO save(BackgroundDTO dto) {
		return new BackgroundDTO(repository.save(dto.toEntity()));
	}

	public boolean existsById(Long id) {
		return repository.existsById(id);
	}

}
