package com.quotevaultapi.service.impl;

import com.quotevaultapi.entity.AccountEntity;
import com.quotevaultapi.repository.AccountRepository;
import com.quotevaultapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public AccountEntity save(AccountEntity accountEntity) {
        enrichAccount(accountEntity);
        return accountRepository.save(accountEntity);
    }

    private void enrichAccount(AccountEntity accountEntity) {
        accountEntity.setCreatedAt(LocalDateTime.now());
    }
}