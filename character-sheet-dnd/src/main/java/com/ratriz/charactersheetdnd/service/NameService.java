package com.ratriz.charactersheetdnd.service;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ratriz.charactersheetdnd.domain.Name;
import com.ratriz.charactersheetdnd.dto.NameDTO;
import com.ratriz.charactersheetdnd.infrastructure.ConstantFilter;
import com.ratriz.charactersheetdnd.infrastructure.ConstantPages;
import com.ratriz.charactersheetdnd.repository.NameRepository;
import com.ratriz.charactersheetdnd.util.ParseUtil;

@Service
public class NameService extends AbstractService<Name, Long> {

	@Autowired
	private NameRepository repository;

	@Override
	protected JpaRepository<Name, Long> getRepository() {
		return this.repository;
	}
	
	@Override
	public Page<NameDTO> findDto(Map<String, String> params) {
		return findDto(params, ConstantPages.PAGE_REQUEST);
	}

	@Override
	public Page<NameDTO> findDto(Map<String, String> params, Pageable pageRequest) {
		return repository.findDto(
				ParseUtil.parseLong(params.get(ConstantFilter.ID_NOT_EQUALS)),
				params.get(ConstantFilter.NAME_LIKE),
				ParseUtil.parseCharacter(params.get(ConstantFilter.GENDER_EQUALS)),
				ParseUtil.parseBoolean(params.get(ConstantFilter.INACTIVE_EQUALS)),
				pageRequest);
	}
	
	@Override
	public NameDTO findOneRandom(Map<String, String> params) {
		return findDto(params,
				PageRequest.of(new Random().nextInt(
					repository.countByFilter(
						ParseUtil.parseLong(params.get(ConstantFilter.ID_NOT_EQUALS)),
						params.get(ConstantFilter.NAME_LIKE),
						ParseUtil.parseCharacter(params.get(ConstantFilter.GENDER_EQUALS)),
						ParseUtil.parseBoolean(params.get(ConstantFilter.INACTIVE_EQUALS))
					).intValue()
				), 1)).getContent().parallelStream().findFirst().orElse(null);
	}
	
}
