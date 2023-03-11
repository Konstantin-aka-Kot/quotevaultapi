package com.quotevaultapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quotevaultapi.dto.AccountEntityDTO;
import com.quotevaultapi.entity.AccountEntity;
import org.modelmapper.ModelMapper;

import java.io.IOException;

public class TestUtil {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static AccountEntityDTO createAccountEntityDTO() {
        AccountEntityDTO accountEntityDTO = new AccountEntityDTO();
        // set properties on accountEntityDTO as desired for test cases
        return accountEntityDTO;
    }

    public static AccountEntity convertToAccountEntity(AccountEntityDTO accountEntityDTO) {
        return modelMapper.map(accountEntityDTO, AccountEntity.class);
    }

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsBytes(object);
    }
}