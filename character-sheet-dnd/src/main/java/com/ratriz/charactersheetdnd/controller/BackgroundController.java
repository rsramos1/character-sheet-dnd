package com.ratriz.charactersheetdnd.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ratriz.charactersheetdnd.dto.BackgroundDTO;
import com.ratriz.charactersheetdnd.infrastructure.ConstantPages;
import com.ratriz.charactersheetdnd.service.BackgroundService;

@RestController
@RequestMapping(ConstantPages.PAGE_API + ConstantPages.PAGE_BACKGROUND)
public class BackgroundController {

	@Autowired
	private BackgroundService service;

	@ResponseBody
	@GetMapping(path = ConstantPages.PAGE_DTO + ConstantPages.PAGE_LIST)
	public ResponseEntity<Page<BackgroundDTO>> findAllDto() {
		return ResponseEntity.ok(service.findAllDto(ConstantPages.PAGE_REQUEST_ORDER_NAME));
	}

	@ResponseBody
	@GetMapping(path = ConstantPages.PAGE_DTO + ConstantPages.PAGE_LIST + ConstantPages.PAGE_SEARCH)
	public ResponseEntity<Page<BackgroundDTO>> findDto(@RequestParam Map<String, String> params, Pageable pageRequest) {
		return ResponseEntity.ok(service.findDto(params, pageRequest));
	}

	@ResponseBody
	@PostMapping(path = ConstantPages.PAGE_SAVE + "/{id}")
	public ResponseEntity<BackgroundDTO> update(@RequestParam Long id, @RequestBody BackgroundDTO dto) {
		if (service.existsById(id)) {
			return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
		}
		return ResponseEntity.unprocessableEntity().body(dto);
	}

	@ResponseBody
	@PostMapping(path = ConstantPages.PAGE_SAVE)
	public ResponseEntity<BackgroundDTO> insert(@RequestBody BackgroundDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
	}

}
