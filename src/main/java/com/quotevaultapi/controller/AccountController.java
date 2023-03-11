package com.quotevaultapi.controller;

import com.quotevaultapi.dto.AccountEntityDTO;
import com.quotevaultapi.entity.AccountEntity;
import com.quotevaultapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.modelmapper.ModelMapper;

@RestController
@RequestMapping("/users")
public class AccountController {

    private final AccountService accountService;
    private final ModelMapper modelMapper;

    @Autowired
    public AccountController(AccountService accountService, ModelMapper modelMapper) {
        this.accountService = accountService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createAccount(@RequestBody AccountEntityDTO accountEntityDTO) {
        accountService.save(convertToAccountEntity(accountEntityDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    AccountEntity convertToAccountEntity(AccountEntityDTO accountEntityDTO) {
        return modelMapper.map(accountEntityDTO, AccountEntity.class);
    }

    AccountEntityDTO convertToAccountEntityDTO(AccountEntity accountEntity) {
        return modelMapper.map(accountEntity, AccountEntityDTO.class);
    }
}