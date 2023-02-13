package com.quotevaultapi.services;

import com.quotevaultapi.models.Quote;
import com.quotevaultapi.repositories.QuoteRepository;
import com.quotevaultapi.util.QuoteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public Quote createQuote(Quote quote) {
        return quoteRepository.save(quote);
    }

    public List<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }

    public Quote getQuoteById(Long id) {
        return quoteRepository.findById(id).orElseThrow(() -> new QuoteNotFoundException(id));
    }

    public Quote updateQuote(Long id, Quote quote) {
        Quote existingQuote = quoteRepository.findById(id).orElseThrow(() -> new QuoteNotFoundException(id));
        existingQuote.setText(quote.getText());
        existingQuote.setAuthor(quote.getAuthor());
        return quoteRepository.save(existingQuote);
    }

    public void deleteQuote(Long id) {
        quoteRepository.deleteById(id);
    }

    public Quote upvoteQuote(Long id) {
        Quote quote = quoteRepository.findById(id).orElseThrow(() -> new QuoteNotFoundException(id));
        quote.setVotes(quote.getVotes() + 1);
        return quoteRepository.save(quote);
    }

    public Quote downvoteQuote(Long id) {
        Quote quote = quoteRepository.findById(id).orElseThrow(() -> new QuoteNotFoundException(id));
        quote.setVotes(quote.getVotes() - 1);
        return quoteRepository.save(quote);
    }

    public List<Quote> getTopQuotes() {
        return quoteRepository.findTop10ByOrderByVotesDesc();
    }

    public List<Quote> getWorseQuotes() {
        return quoteRepository.findTop10ByOrderByScoreAsc();
    }
}
