package com.ratriz.charactersheetdnd.controller;

import com.google.gson.JsonObject;
import com.ratriz.charactersheetdnd.domain.dto.CharacterSheetDTO;
import com.ratriz.charactersheetdnd.domain.dto.IDTO;
import com.ratriz.charactersheetdnd.infrastructure.ConstantPages;
import com.ratriz.charactersheetdnd.service.CharacterSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(ConstantPages.ROUTE_API + ConstantPages.ROUTE_CHARACTER_SHEET)
public class CharacterSheetController {

    @Autowired
    private CharacterSheetService service;

    @ResponseBody
    @GetMapping(path = ConstantPages.ROUTE_DTO + ConstantPages.ROUTE_ATTRIBUTE_ID)
    public ResponseEntity<CharacterSheetDTO> findByIdDto(@PathVariable(ConstantPages.ATTRIBUTE_ID) Long id) {
        return ResponseEntity.ok(service.findByIdDto(id));
    }

    @ResponseBody
    @PostMapping(path = ConstantPages.ROUTE_DTO + ConstantPages.ROUTE_LIST)
    public ResponseEntity<Page<CharacterSheetDTO>> findAllDto() {
        return ResponseEntity.ok(service.findAllDto(ConstantPages.PAGE_REQUEST));
    }

    @ResponseBody
    @PostMapping(path = ConstantPages.ROUTE_DTO + ConstantPages.ROUTE_SEARCH)
    public ResponseEntity<Page<CharacterSheetDTO>> findDto(@RequestParam Map<String, String> params,
                                                           Pageable pageRequest) {
        return ResponseEntity.ok(service.findAllDto(pageRequest));
    }

    @ResponseBody
    @PostMapping(path = ConstantPages.ROUTE_DTO + ConstantPages.ROUTE_GENERATE)
    public ResponseEntity<CharacterSheetDTO> generate(@RequestParam JsonObject json) {
        return ResponseEntity.ok(service.generate(json));
    }

    @ResponseBody
    @PostMapping(path = ConstantPages.ROUTE_ACTION)
    public ResponseEntity<CharacterSheetDTO> insert(@RequestBody CharacterSheetDTO dto) {
        return ResponseEntity.ok(service.insert(dto));
    }

    @ResponseBody
    @PutMapping(path = ConstantPages.ROUTE_ACTION + ConstantPages.ROUTE_ATTRIBUTE_ID)
    public ResponseEntity<CharacterSheetDTO> update(@PathVariable(ConstantPages.ATTRIBUTE_ID) Long id,
                                                    @RequestBody CharacterSheetDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @ResponseBody
    @DeleteMapping(path = ConstantPages.ROUTE_ACTION + ConstantPages.ROUTE_ATTRIBUTE_ID)
    public ResponseEntity<CharacterSheetDTO> delete(@PathVariable(ConstantPages.ATTRIBUTE_ID) Long id) {
        return ResponseEntity.ok(service.delete(id).toDTO());
    }
// TODO implementar find by filter
}
