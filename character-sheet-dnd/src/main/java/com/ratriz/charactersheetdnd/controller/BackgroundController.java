package com.ratriz.charactersheetdnd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratriz.charactersheetdnd.domain.Background;
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

}
