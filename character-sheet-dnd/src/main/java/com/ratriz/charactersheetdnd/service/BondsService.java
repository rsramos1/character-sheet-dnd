package com.ratriz.charactersheetdnd.service;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ratriz.charactersheetdnd.domain.Bonds;
import com.ratriz.charactersheetdnd.dto.BondsDTO;
import com.ratriz.charactersheetdnd.infrastructure.ConstantFilter;
import com.ratriz.charactersheetdnd.repository.BondsRepository;
import com.ratriz.charactersheetdnd.util.ParseUtil;

@Service
public class BondsService extends AbstractService<Bonds, Long> {

	@Autowired
	private BondsRepository repository;

	@Override
	protected JpaRepository<Bonds, Long> getRepository() {
		return this.repository;
	}

	@Override
	public Page<BondsDTO> findDto(Map<String, String> params, Pageable pageRequest) {
		return repository.findDto(
				ParseUtil.parseLong(params.get(ConstantFilter.ID_NOT_EQUALS)),
				ParseUtil.parseLong(params.get(ConstantFilter.BACKGROUND_ID_EQUALS)),
				params.get(ConstantFilter.NAME_LIKE),
				ParseUtil.parseBoolean(params.get(ConstantFilter.INACTIVE_EQUALS)),
				pageRequest);
	}

	@Override
	public BondsDTO findOneRandom(Map<String, String> params) {
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
