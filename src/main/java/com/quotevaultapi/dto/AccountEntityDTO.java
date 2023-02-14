package com.quotevaultapi.dto;

import com.quotevaultapi.entities.QuoteEntity;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class AccountEntityDTO {
    private String name;
    private String email;
    private String password;
    private List<QuoteEntity> quotes;
}
