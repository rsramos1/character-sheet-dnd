package com.ratriz.charactersheetdnd.controller;

import com.google.gson.JsonObject;
import com.ratriz.charactersheetdnd.domain.dto.UserDTO;
import com.ratriz.charactersheetdnd.domain.dto.UserSignUpDTO;
import com.ratriz.charactersheetdnd.infrastructure.ConstantPages;
import com.ratriz.charactersheetdnd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ConstantPages.ROUTE_API + ConstantPages.ROUTE_USER)
public class UserController {

    @Autowired
    private UserService service;

    @ResponseBody
    @PostMapping(path = ConstantPages.ROUTE_SIGN_UP)
    public ResponseEntity<UserDTO> signUp(@RequestBody UserSignUpDTO dto) {
        return ResponseEntity.ok(service.signUp(dto));
    }

    @ResponseBody
    @PostMapping(path = ConstantPages.ROUTE_LOGIN)
    public ResponseEntity<UserDTO> login(@RequestBody JsonObject json) {
        return ResponseEntity.ok(service.login(json));
    }

    @ResponseBody
    @PutMapping(path = ConstantPages.ROUTE_ACTION + ConstantPages.ROUTE_CHANGE_PASSWORD + ConstantPages.ROUTE_ATTRIBUTE_ID)
    public ResponseEntity changePassword(@PathVariable Long id, @RequestBody JsonObject json) {
        service.changePassword(id, json);
        return ResponseEntity.ok().build();
    }

}
