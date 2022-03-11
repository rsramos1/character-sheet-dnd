package com.ratriz.charactersheetdnd.service;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ratriz.charactersheetdnd.domain.Background;
import com.ratriz.charactersheetdnd.dto.BackgroundDTO;
import com.ratriz.charactersheetdnd.dto.BondsDTO;
import com.ratriz.charactersheetdnd.dto.FlawsDTO;
import com.ratriz.charactersheetdnd.dto.IdealsDTO;
import com.ratriz.charactersheetdnd.dto.PersonalityTraitsDTO;
import com.ratriz.charactersheetdnd.infrastructure.ConstantFilter;
import com.ratriz.charactersheetdnd.infrastructure.ConstantPages;
import com.ratriz.charactersheetdnd.repository.BackgroundRepository;
import com.ratriz.charactersheetdnd.util.ParseUtil;

@Service
public class BackgroundService extends AbstractService<Background, Long> {

	@Autowired
	private BackgroundRepository repository;

	@Autowired
	private PersonalityTraitsService personalityTraitsService;

	@Autowired
	private IdealsService idealsService;
	
	@Autowired
	private BondsService bondsService;
	
	@Autowired
	private FlawsService flawsService;

	@Override
	protected JpaRepository<Background, Long> getRepository() {
		return this.repository;
	}
	
	@Override
	public Page<BackgroundDTO> findDto(Map<String, String> params) {
		return findDto(params, ConstantPages.PAGE_REQUEST);
	}

	@Override
	public Page<BackgroundDTO> findDto(Map<String, String> params, Pageable pageRequest) {
		return repository.findDto(
				ParseUtil.parseLong(params.get(ConstantFilter.ID_NOT_EQUALS)),
				params.get(ConstantFilter.NAME_LIKE),
				ParseUtil.parseBoolean(params.get(ConstantFilter.INACTIVE_EQUALS)),
				pageRequest);
	}
	
	@Override
	public BackgroundDTO findOneRandom(Map<String, String> params) {
		return findDto(params,
				PageRequest.of(new Random().nextInt(
					repository.countByFilter(
						ParseUtil.parseLong(params.get(ConstantFilter.ID_NOT_EQUALS)),
						params.get(ConstantFilter.NAME_LIKE),
						ParseUtil.parseBoolean(params.get(ConstantFilter.INACTIVE_EQUALS))
					).intValue()
				), 1)).getContent().parallelStream().findFirst().orElse(null);
	}
	
	@SuppressWarnings("unchecked")
	public Page<PersonalityTraitsDTO> findPersonalityTraitsDtoById(Long id) {
		return (Page<PersonalityTraitsDTO>) personalityTraitsService
				.findDto(Map.of(ConstantFilter.BACKGROUND_ID_EQUALS, id.toString()));
	}

	@SuppressWarnings("unchecked")
	public Page<IdealsDTO> findIdealsDtoById(Long id) {
		return (Page<IdealsDTO>) idealsService.findDto(Map.of(ConstantFilter.BACKGROUND_ID_EQUALS, id.toString()));
	}

	@SuppressWarnings("unchecked")
	public Page<BondsDTO> findBondsDtoById(Long id) {
		return (Page<BondsDTO>) bondsService.findDto(Map.of(ConstantFilter.BACKGROUND_ID_EQUALS, id.toString()));
	}

	@SuppressWarnings("unchecked")
	public Page<FlawsDTO> findFlawsDtoById(Long id) {
		return (Page<FlawsDTO>) flawsService.findDto(Map.of(ConstantFilter.BACKGROUND_ID_EQUALS, id.toString()));
	}

}
