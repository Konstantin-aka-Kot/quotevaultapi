package com.quotevaultapi.services.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.quotevaultapi.entities.AccountEntity;
import com.quotevaultapi.repositories.AccountRepository;


class AccountServiceImplTest {

    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        accountService = new AccountServiceImpl(accountRepository);
    }

    @Test
    void testSave() {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setName("testUser");
        accountEntity.setPassword("testPassword");
        accountService.save(accountEntity);
        assertNotNull(accountEntity.getCreatedAt());
        assertEquals(LocalDateTime.now().getDayOfYear(), accountEntity.getCreatedAt().getDayOfYear());
    }
}
