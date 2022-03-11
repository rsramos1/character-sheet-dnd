package com.ratriz.charactersheetdnd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ratriz.charactersheetdnd.domain.Background;
import com.ratriz.charactersheetdnd.dto.BackgroundDTO;
import com.ratriz.charactersheetdnd.dto.BondsDTO;
import com.ratriz.charactersheetdnd.dto.FlawsDTO;
import com.ratriz.charactersheetdnd.dto.IdealsDTO;
import com.ratriz.charactersheetdnd.dto.PersonalityTraitsDTO;
import com.ratriz.charactersheetdnd.infrastructure.ConstantPages;
import com.ratriz.charactersheetdnd.service.AbstractService;
import com.ratriz.charactersheetdnd.service.BackgroundService;

@RestController
@RequestMapping(ConstantPages.PAGE_API + ConstantPages.PAGE_BACKGROUND)
public class BackgroundController extends AbstractController<Background, Long> {

	@Autowired
	private BackgroundService service;

	@Override
	protected AbstractService<Background, Long> getService() {
		return service;
	}

	@ResponseBody
	@PostMapping(path = ConstantPages.PAGE_ACTION)
	public ResponseEntity<BackgroundDTO> insert(@RequestBody BackgroundDTO dto) {
		return ResponseEntity.ok(getService().insert(dto));
	}

	@ResponseBody
	@PutMapping(path = ConstantPages.PAGE_ACTION + ConstantPages.PAGE_ATTRIBUTE_ID)
	public ResponseEntity<BackgroundDTO> update(@PathVariable(ConstantPages.ATTRIBUTE_ID) Long id,
			@RequestBody BackgroundDTO dto) {
		return ResponseEntity.ok(getService().update(id, dto));
	}

	@ResponseBody
	@GetMapping(path = ConstantPages.PAGE_DTO + ConstantPages.PAGE_ATTRIBUTE_ID + ConstantPages.PAGE_PERSONALITY_TRAITS)
	public ResponseEntity<Page<PersonalityTraitsDTO>> findPersonalityTraitsDtoById(@PathVariable(ConstantPages.ATTRIBUTE_ID) Long id) {
		return ResponseEntity.ok(service.findPersonalityTraitsDtoById(id));
	}
	
	@ResponseBody
	@GetMapping(path = ConstantPages.PAGE_DTO + ConstantPages.PAGE_ATTRIBUTE_ID + ConstantPages.PAGE_IDEALS)
	public ResponseEntity<Page<IdealsDTO>> findIdealsDtoById(@PathVariable(ConstantPages.ATTRIBUTE_ID) Long id) {
		return ResponseEntity.ok(service.findIdealsDtoById(id));
	}
	
	@ResponseBody
	@GetMapping(path = ConstantPages.PAGE_DTO + ConstantPages.PAGE_ATTRIBUTE_ID + ConstantPages.PAGE_BONDS)
	public ResponseEntity<Page<BondsDTO>> findBondsDtoById(@PathVariable(ConstantPages.ATTRIBUTE_ID) Long id) {
		return ResponseEntity.ok(service.findBondsDtoById(id));
	}
	
	@ResponseBody
	@GetMapping(path = ConstantPages.PAGE_DTO + ConstantPages.PAGE_ATTRIBUTE_ID + ConstantPages.PAGE_FLAWS)
	public ResponseEntity<Page<FlawsDTO>> findFlawsDtoById(@PathVariable(ConstantPages.ATTRIBUTE_ID) Long id) {
		return ResponseEntity.ok(service.findFlawsDtoById(id));
	}

}
