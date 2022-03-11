package com.ratriz.charactersheetdnd.service;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ratriz.charactersheetdnd.domain.Race;
import com.ratriz.charactersheetdnd.dto.RaceDTO;
import com.ratriz.charactersheetdnd.infrastructure.ConstantFilter;
import com.ratriz.charactersheetdnd.infrastructure.ConstantPages;
import com.ratriz.charactersheetdnd.repository.RaceRepository;
import com.ratriz.charactersheetdnd.util.ParseUtil;

@Service
public class RaceService extends AbstractService<Race, Long> {

	@Autowired
	private RaceRepository repository;

	@Override
	protected JpaRepository<Race, Long> getRepository() {
		return this.repository;
	}
	
	@Override
	public Page<RaceDTO> findDto(Map<String, String> params) {
		return findDto(params, ConstantPages.PAGE_REQUEST);
	}

	@Override
	public Page<RaceDTO> findDto(Map<String, String> params, Pageable pageRequest) {
		return repository.findDto(
				ParseUtil.parseLong(params.get(ConstantFilter.ID_NOT_EQUALS)),
				params.get(ConstantFilter.NAME_LIKE),
				ParseUtil.parseBoolean(params.get(ConstantFilter.INACTIVE_EQUALS)),
				pageRequest);
	}
	
	@Override
	public RaceDTO findOneRandom(Map<String, String> params) {
		return findDto(params,
				PageRequest.of(new Random().nextInt(
					repository.countByFilter(
						ParseUtil.parseLong(params.get(ConstantFilter.ID_NOT_EQUALS)),
						params.get(ConstantFilter.NAME_LIKE),
						ParseUtil.parseBoolean(params.get(ConstantFilter.INACTIVE_EQUALS))
					).intValue()
				), 1)).getContent().parallelStream().findFirst().orElse(null);
	}
	
}
