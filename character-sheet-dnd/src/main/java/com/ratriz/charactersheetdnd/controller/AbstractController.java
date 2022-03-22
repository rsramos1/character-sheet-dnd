package com.ratriz.charactersheetdnd.controller;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ratriz.charactersheetdnd.domain.dto.IDTO;
import com.ratriz.charactersheetdnd.domain.entity.AbstractEntity;
import com.ratriz.charactersheetdnd.infrastructure.ConstantPages;
import com.ratriz.charactersheetdnd.service.AbstractService;

@RestController
public abstract class AbstractController<T extends AbstractEntity<K>, K> {

    protected abstract AbstractService<T, K> getService();

    @ResponseBody
    @GetMapping(path = ConstantPages.ROUTE_DTO + ConstantPages.ROUTE_LIST)
    public ResponseEntity<Page<IDTO<T>>> findAllDto() {
        return ResponseEntity.ok(getService().findAllDto(ConstantPages.PAGE_REQUEST_ORDER_NAME));
    }

    @ResponseBody
    @GetMapping(path = ConstantPages.ROUTE_DTO + ConstantPages.ROUTE_ATTRIBUTE_ID)
    public ResponseEntity<IDTO<T>> findByIdDto(@PathVariable(ConstantPages.ATTRIBUTE_ID) K id) {
        return ResponseEntity.ok(getService().findByIdDto(id));
    }

    @ResponseBody
    @GetMapping(path = ConstantPages.ROUTE_DTO + ConstantPages.ROUTE_LIST + ConstantPages.ROUTE_SEARCH)
    public ResponseEntity<Page<? extends IDTO<T>>> findDto(@RequestParam Map<String, String> params,
                                                           Pageable pageRequest) {
        return ResponseEntity.ok(getService().findDto(params, pageRequest));
    }

    @ResponseBody
    @PutMapping(path = ConstantPages.ROUTE_ACTION + ConstantPages.ROUTE_CHANGE_STATUS + ConstantPages.ROUTE_ATTRIBUTE_ID)
    public ResponseEntity<IDTO<T>> changeStatus(@PathVariable(ConstantPages.ATTRIBUTE_ID) K id) {
        return ResponseEntity.ok(getService().changeStatus(id));
    }

    @ResponseBody
    @DeleteMapping(path = ConstantPages.ROUTE_ACTION + ConstantPages.ROUTE_ATTRIBUTE_ID)
    public ResponseEntity<IDTO<T>> delete(@PathVariable(ConstantPages.ATTRIBUTE_ID) K id) {
        return ResponseEntity.ok(getService().delete(id).toDTO());
    }

    @ResponseBody
    @GetMapping(path = ConstantPages.ROUTE_DTO + ConstantPages.ROUTE_RANDOM)
    public ResponseEntity<IDTO<T>> findOneRandom(@RequestParam Map<String, String> params, Pageable pageRequest) {
        return ResponseEntity.ok(getService().findOneRandom(params));
    }

}
