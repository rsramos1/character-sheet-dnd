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

import com.ratriz.charactersheetdnd.domain.dto.FamilyNameDTO;
import com.ratriz.charactersheetdnd.domain.entity.FamilyName;
import com.ratriz.charactersheetdnd.infrastructure.ConstantPages;
import com.ratriz.charactersheetdnd.service.AbstractService;
import com.ratriz.charactersheetdnd.service.FamilyNameService;

@RestController
@RequestMapping(ConstantPages.ROUTE_API + ConstantPages.ROUTE_FAMILY_NAME)
public class FamilyNameController extends AbstractController<FamilyName, Long> {

	@Autowired
	private FamilyNameService service;

	@Override
	protected AbstractService<FamilyName, Long> getService() {
		return service;
	}

	@ResponseBody
	@PostMapping(path = ConstantPages.ROUTE_ACTION)
	public ResponseEntity<FamilyNameDTO> insert(@RequestBody FamilyNameDTO dto) {
		return ResponseEntity.ok(getService().insert(dto));
	}

	@ResponseBody
	@PutMapping(path = ConstantPages.ROUTE_ACTION + ConstantPages.ROUTE_ATTRIBUTE_ID)
	public ResponseEntity<FamilyNameDTO> update(@PathVariable(ConstantPages.ATTRIBUTE_ID) Long id,
			@RequestBody FamilyNameDTO dto) {
		return ResponseEntity.ok(getService().update(id, dto));
	}

}
