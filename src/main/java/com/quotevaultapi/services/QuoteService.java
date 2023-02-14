package com.quotevaultapi.services;


import com.quotevaultapi.entities.QuoteEntity;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface QuoteService {
    QuoteEntity save(QuoteEntity quote);
    List<QuoteEntity> getAllQuotes();
    List<QuoteEntity> getAllQuotes(Integer limit, Sort.Direction direction);
    QuoteEntity getQuoteById(Long id);
    QuoteEntity updateQuote(Long id, QuoteEntity quote);
    void deleteQuote(Long id);
    QuoteEntity upvoteQuote(Long id);
    QuoteEntity downvoteQuote(Long id);
}
