package com.quotevaultapi.controller;

import com.quotevaultapi.dto.AccountEntityDTO;
import com.quotevaultapi.entity.AccountEntity;
import com.quotevaultapi.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountController.class)
class AccountsControllerTest {

    @Mock
    private AccountService accountService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AccountController accountsController;

    AccountEntityDTO accountEntityDTO;
    AccountEntity accountEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        accountEntityDTO = new AccountEntityDTO();
        accountEntity = new AccountEntity();
    }

    @Test
    @DisplayName("Create account - Success")
    void testCreateAccountReturnsOK() {
        // Arrange
        when(modelMapper.map(accountEntityDTO, AccountEntity.class)).thenReturn(accountEntity);

        // Act
        ResponseEntity<HttpStatus> response = accountsController.createAccount(accountEntityDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(accountService).save(accountEntity);
    }

    @Test
    @DisplayName("Create new account with exception")
    void testCreateAccountThrowsException() {
        // Arrange
        when(modelMapper.map(accountEntityDTO, AccountEntity.class)).thenThrow(RuntimeException.class);

        // Act
        ResponseEntity<HttpStatus> response = accountsController.createAccount(accountEntityDTO);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(accountService).save(any(AccountEntity.class));
    }

    @Test
    @DisplayName("Test convert to account")
    void testConvertToAccountEntity() {
        // Arrange
        when(modelMapper.map(accountEntityDTO, AccountEntity.class)).thenReturn(accountEntity);

        // Act
        AccountEntity result = accountsController.convertToAccountEntity(accountEntityDTO);

        // Assert
        assertEquals(accountEntity, result);
    }

    @Test
    @DisplayName("Test convert to accountDTO")
    void testConvertToAccountEntityDTO() {
        // Arrange
        when(modelMapper.map(accountEntity, AccountEntityDTO.class)).thenReturn(accountEntityDTO);

        // Act
        AccountEntityDTO result = accountsController.convertToAccountEntityDTO(accountEntity);

        // Assert
        assertEquals(accountEntityDTO, result);
    }

}