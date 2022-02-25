package com.ratriz.charactersheetdnd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratriz.charactersheetdnd.dto.BackgroundDTO;
import com.ratriz.charactersheetdnd.repository.BackgroundRepository;

@RestController
@RequestMapping("/background")
public class BackgroundController {

	@Autowired
	private BackgroundRepository repository;

	public List<BackgroundDTO> findAllDto() {
		return repository.findAllDto();
	}

}
