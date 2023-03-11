package com.quotevaultapi.dto;

import com.quotevaultapi.entity.AccountEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuoteEntityDTO {
    private String text;
    private AccountEntity account;
    private int votes;
}
