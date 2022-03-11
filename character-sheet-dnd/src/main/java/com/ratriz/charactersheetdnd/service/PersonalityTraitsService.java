package com.ratriz.charactersheetdnd.service;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ratriz.charactersheetdnd.domain.dto.PersonalityTraitsDTO;
import com.ratriz.charactersheetdnd.domain.entity.PersonalityTraits;
import com.ratriz.charactersheetdnd.infrastructure.ConstantFilter;
import com.ratriz.charactersheetdnd.repository.PersonalityTraitsRepository;
import com.ratriz.charactersheetdnd.util.ParseUtil;

@Service
public class PersonalityTraitsService extends AbstractService<PersonalityTraits, Long> {

	@Autowired
	private PersonalityTraitsRepository repository;

	@Override
	protected JpaRepository<PersonalityTraits, Long> getRepository() {
		return this.repository;
	}

	@Override
	public Page<PersonalityTraitsDTO> findDto(Map<String, String> params, Pageable pageRequest) {
		return repository.findDto(
				ParseUtil.parseLong(params.get(ConstantFilter.ID_NOT_EQUALS)),
				ParseUtil.parseLong(params.get(ConstantFilter.BACKGROUND_ID_EQUALS)),
				params.get(ConstantFilter.NAME_LIKE),
				ParseUtil.parseBoolean(params.get(ConstantFilter.INACTIVE_EQUALS)),
				pageRequest);
	}

	@Override
	public PersonalityTraitsDTO findOneRandom(Map<String, String> params) {
		return findDto(params,
				PageRequest.of(new Random().nextInt(
					repository.countByFilter(
						ParseUtil.parseLong(params.get(ConstantFilter.ID_NOT_EQUALS)),
						ParseUtil.parseLong(params.get(ConstantFilter.BACKGROUND_ID_EQUALS)),
						params.get(ConstantFilter.NAME_LIKE),
						ParseUtil.parseBoolean(params.get(ConstantFilter.INACTIVE_EQUALS))
					).intValue()
				), 1)).getContent().parallelStream().findFirst().orElse(null);
	}

}
