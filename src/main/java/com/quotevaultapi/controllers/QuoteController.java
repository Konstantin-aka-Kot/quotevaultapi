package com.quotevaultapi.controllers;

import com.quotevaultapi.models.Quote;
import com.quotevaultapi.services.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

    private final QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @PostMapping
    public Quote createQuote(@RequestBody Quote quote) {
        return quoteService.createQuote(quote);
    }

    @GetMapping
    public List<Quote> getAllQuotes() {
        return quoteService.getAllQuotes();
    }

    @GetMapping("/{id}")
    public Quote getQuoteById(@PathVariable Long id) {
        return quoteService.getQuoteById(id);
    }

    @PutMapping("/{id}")
    public Quote updateQuote(@PathVariable Long id, @RequestBody Quote quote) {
        return quoteService.updateQuote(id, quote);
    }

    @DeleteMapping("/{id}")
    public void deleteQuote(@PathVariable Long id) {
        quoteService.deleteQuote(id);
    }

    @PostMapping("/{id}/upvote")
    public Quote upvoteQuote(@PathVariable Long id) {
        return quoteService.upvoteQuote(id);
    }

    @PostMapping("/{id}/downvote")
    public Quote downvoteQuote(@PathVariable Long id) {
        return quoteService.downvoteQuote(id);
    }

    @GetMapping("/top")
    public List<Quote> getTopQuotes() {
        return quoteService.getTopQuotes();
    }

    @GetMapping("/worse")
    public List<Quote> getWorseQuotes() {
        return quoteService.getWorseQuotes();
    }
}