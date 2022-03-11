package com.ratriz.charactersheetdnd.service;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ratriz.charactersheetdnd.domain.dto.SubclassDTO;
import com.ratriz.charactersheetdnd.domain.entity.Subclass;
import com.ratriz.charactersheetdnd.infrastructure.ConstantFilter;
import com.ratriz.charactersheetdnd.repository.SubclassRepository;
import com.ratriz.charactersheetdnd.util.ParseUtil;

@Service
public class SubclassService extends AbstractService<Subclass, Long> {

	@Autowired
	private SubclassRepository repository;

	@Override
	protected JpaRepository<Subclass, Long> getRepository() {
		return this.repository;
	}

	@Override
	public Page<SubclassDTO> findDto(Map<String, String> params, Pageable pageRequest) {
		return repository.findDto(
				ParseUtil.parseLong(params.get(ConstantFilter.ID_NOT_EQUALS)),
				ParseUtil.parseLong(params.get(ConstantFilter.CHARACTER_CLASS_ID_EQUALS)),
				params.get(ConstantFilter.NAME_LIKE),
				ParseUtil.parseBoolean(params.get(ConstantFilter.INACTIVE_EQUALS)),
				pageRequest);
	}

	@Override
	public SubclassDTO findOneRandom(Map<String, String> params) {
		return findDto(params,
				PageRequest.of(new Random().nextInt(
					repository.countByFilter(
						ParseUtil.parseLong(params.get(ConstantFilter.ID_NOT_EQUALS)),
						ParseUtil.parseLong(params.get(ConstantFilter.CHARACTER_CLASS_ID_EQUALS)),
						params.get(ConstantFilter.NAME_LIKE),
						ParseUtil.parseBoolean(params.get(ConstantFilter.INACTIVE_EQUALS))
					).intValue()
				), 1)).getContent().parallelStream().findFirst().orElse(null);
	}

}
