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

import com.ratriz.charactersheetdnd.domain.dto.BackgroundDTO;
import com.ratriz.charactersheetdnd.domain.entity.Background;
import com.ratriz.charactersheetdnd.infrastructure.ConstantPages;
import com.ratriz.charactersheetdnd.service.AbstractService;
import com.ratriz.charactersheetdnd.service.BackgroundService;

@RestController
@RequestMapping(ConstantPages.ROUTE_API + ConstantPages.ROUTE_BACKGROUND)
public class BackgroundController extends AbstractController<Background, Long> {

	@Autowired
	private BackgroundService service;

	@Override
	protected AbstractService<Background, Long> getService() {
		return service;
	}

	@ResponseBody
	@PostMapping(path = ConstantPages.ROUTE_ACTION)
	public ResponseEntity<BackgroundDTO> insert(@RequestBody BackgroundDTO dto) {
		return ResponseEntity.ok(getService().insert(dto));
	}

	@ResponseBody
	@PutMapping(path = ConstantPages.ROUTE_ACTION + ConstantPages.ROUTE_ATTRIBUTE_ID)
	public ResponseEntity<BackgroundDTO> update(@PathVariable(ConstantPages.ATTRIBUTE_ID) Long id,
			@RequestBody BackgroundDTO dto) {
		return ResponseEntity.ok(getService().update(id, dto));
	}

}
