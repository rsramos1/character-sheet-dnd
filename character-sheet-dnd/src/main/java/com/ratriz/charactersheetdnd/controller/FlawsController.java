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

import com.ratriz.charactersheetdnd.domain.Flaws;
import com.ratriz.charactersheetdnd.dto.FlawsDTO;
import com.ratriz.charactersheetdnd.infrastructure.ConstantPages;
import com.ratriz.charactersheetdnd.service.AbstractService;
import com.ratriz.charactersheetdnd.service.FlawsService;

@RestController
@RequestMapping(ConstantPages.PAGE_API + ConstantPages.PAGE_FLAWS)
public class FlawsController extends AbstractController<Flaws, Long> {

	@Autowired
	private FlawsService service;

	@Override
	protected AbstractService<Flaws, Long> getService() {
		return service;
	}

	@ResponseBody
	@PostMapping(path = ConstantPages.PAGE_ACTION)
	public ResponseEntity<FlawsDTO> insert(@RequestBody FlawsDTO dto) {
		return ResponseEntity.ok(getService().insert(dto));
	}

	@ResponseBody
	@PutMapping(path = ConstantPages.PAGE_ACTION + ConstantPages.PAGE_ATTRIBUTE_ID)
	public ResponseEntity<FlawsDTO> update(@PathVariable(ConstantPages.ATTRIBUTE_ID) Long id,
			@RequestBody FlawsDTO dto) {
		return ResponseEntity.ok(getService().update(id, dto));
	}

}
