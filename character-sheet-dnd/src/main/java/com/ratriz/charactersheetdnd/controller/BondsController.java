package com.ratriz.charactersheetdnd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratriz.charactersheetdnd.domain.Bonds;
import com.ratriz.charactersheetdnd.infrastructure.ConstantPages;
import com.ratriz.charactersheetdnd.service.AbstractService;
import com.ratriz.charactersheetdnd.service.BondsService;

@RestController
@RequestMapping(ConstantPages.PAGE_API + ConstantPages.PAGE_BONDS)
public class BondsController extends AbstractController<Bonds, Long> {

	@Autowired
	private BondsService service;

	@Override
	protected AbstractService<Bonds, Long> getService() {
		return service;
	}

}
