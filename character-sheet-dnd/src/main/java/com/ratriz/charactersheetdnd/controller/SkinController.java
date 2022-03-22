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

import com.ratriz.charactersheetdnd.domain.dto.SkinDTO;
import com.ratriz.charactersheetdnd.domain.entity.Skin;
import com.ratriz.charactersheetdnd.infrastructure.ConstantPages;
import com.ratriz.charactersheetdnd.service.AbstractService;
import com.ratriz.charactersheetdnd.service.SkinService;

@RestController
@RequestMapping(ConstantPages.ROUTE_API + ConstantPages.ROUTE_SKIN)
public class SkinController extends AbstractController<Skin, Long> {

	@Autowired
	private SkinService service;

	@Override
	protected AbstractService<Skin, Long> getService() {
		return service;
	}

	@ResponseBody
	@PostMapping(path = ConstantPages.ROUTE_ACTION)
	public ResponseEntity<SkinDTO> insert(@RequestBody SkinDTO dto) {
		return ResponseEntity.ok(getService().insert(dto));
	}

	@ResponseBody
	@PutMapping(path = ConstantPages.ROUTE_ACTION + ConstantPages.ROUTE_ATTRIBUTE_ID)
	public ResponseEntity<SkinDTO> update(@PathVariable(ConstantPages.ATTRIBUTE_ID) Long id, @RequestBody SkinDTO dto) {
		return ResponseEntity.ok(getService().update(id, dto));
	}

}
