package com.ratriz.charactersheetdnd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ratriz.charactersheetdnd.domain.CharacterClass;
import com.ratriz.charactersheetdnd.dto.CharacterClassDTO;
import com.ratriz.charactersheetdnd.infrastructure.ConstantPages;
import com.ratriz.charactersheetdnd.service.AbstractService;
import com.ratriz.charactersheetdnd.service.CharacterClassService;

@RestController
@RequestMapping(ConstantPages.PAGE_API + ConstantPages.PAGE_CLASS)
public class CharacterClassController extends AbstractController<CharacterClass, Long> {

	@Autowired
	private CharacterClassService service;

	@Override
	protected AbstractService<CharacterClass, Long> getService() {
		return service;
	}

	@ResponseBody
	@PostMapping(path = ConstantPages.PAGE_ACTION)
	public ResponseEntity<CharacterClassDTO> insert(@RequestBody CharacterClassDTO dto) {
		return ResponseEntity.ok(getService().insert(dto));
	}

	@ResponseBody
	@PutMapping(path = ConstantPages.PAGE_ACTION + ConstantPages.PAGE_ATTRIBUTE_ID)
	public ResponseEntity<CharacterClassDTO> update(@PathVariable(ConstantPages.ATTRIBUTE_ID) Long id,
			@RequestBody CharacterClassDTO dto) {
		return ResponseEntity.ok(getService().update(id, dto));
	}

}
