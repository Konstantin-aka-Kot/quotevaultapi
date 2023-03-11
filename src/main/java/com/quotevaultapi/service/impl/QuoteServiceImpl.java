package com.quotevaultapi.service.impl;

import com.quotevaultapi.entity.QuoteEntity;
import com.quotevaultapi.repository.QuoteRepository;
import com.quotevaultapi.exception.QuoteNotFoundException;
import com.quotevaultapi.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class QuoteServiceImpl implements QuoteService {

    private final QuoteRepository quoteRepository;

    @Autowired
    public QuoteServiceImpl(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @Transactional
    @Override
    public QuoteEntity save(QuoteEntity quote) {
        enrichQuote(quote);
        return quoteRepository.save(quote);
    }

    @Override
    public List<QuoteEntity> getAllQuotes() {
        return quoteRepository.findAll();
    }
    @Override
    public List<QuoteEntity> getAllQuotes(Integer limit, Sort.Direction direction) {
        Sort sort = Sort.by(direction, "votes");
        Pageable pageable = PageRequest.of(0, limit, sort);
        return quoteRepository.findAll(pageable).toList();
    }

    @Override
    public QuoteEntity getQuoteById(Long id) {
        return findById(id);
    }

    @Transactional
    @Override
    public QuoteEntity updateQuote(Long id, QuoteEntity quote) {
        QuoteEntity existingQuote = findById(id);
        existingQuote.setText(quote.getText());
        existingQuote.setAccount(quote.getAccount());
        enrichQuote(existingQuote);
        return quoteRepository.save(existingQuote);
    }

    @Transactional
    @Override
    public void deleteQuote(Long id) {
        quoteRepository.deleteById(id);
    }

    @Transactional
    @Override
    public QuoteEntity upvoteQuote(Long id) {
        QuoteEntity quote = findById(id);
        quote.setVotes(quote.getVotes() + 1);
        enrichQuote(quote);
        return quoteRepository.save(quote);
    }

    @Transactional
    @Override
    public QuoteEntity downvoteQuote(Long id) {
        QuoteEntity quote = findById(id);
        quote.setVotes(quote.getVotes() - 1);
        enrichQuote(quote);
        return quoteRepository.save(quote);
    }

    private QuoteEntity findById(Long id) {
        return quoteRepository.findById(id).orElseThrow(() -> new QuoteNotFoundException(id));
    }

    private void enrichQuote(QuoteEntity quote) {
        if (quote.getCreatedAt() == null) {
            quote.setCreatedAt(LocalDateTime.now());
        }
        quote.setUpdatedAt(LocalDateTime.now());
    }
}
