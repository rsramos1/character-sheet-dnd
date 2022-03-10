package com.ratriz.charactersheetdnd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ratriz.charactersheetdnd.domain.Background;
import com.ratriz.charactersheetdnd.dto.BondsDTO;
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
	@GetMapping(path = ConstantPages.PAGE_DTO + ConstantPages.PAGE_ATTRIBUTE_ID + ConstantPages.PAGE_BONDS)
	public ResponseEntity<Page<BondsDTO>> findBondsDtoById(@PathVariable(ConstantPages.ATTRIBUTE_ID) Long id) {
		return ResponseEntity.ok(service.findBondsDtoById(id));
	}

}
