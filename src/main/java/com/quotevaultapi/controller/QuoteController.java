package com.quotevaultapi.controller;

import com.quotevaultapi.dto.QuoteEntityDTO;
import com.quotevaultapi.entity.QuoteEntity;
import com.quotevaultapi.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;


import java.util.List;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

    private final QuoteService quoteService;
    private final ModelMapper modelMapper;

    @Autowired
    public QuoteController(QuoteService quoteService, ModelMapper modelMapper) {
        this.quoteService = quoteService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createQuote(@RequestBody QuoteEntityDTO quoteEntityDTO) {
        quoteService.save(convertToQuoteEntity(quoteEntityDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public List<QuoteEntityDTO> getAllQuotes() {
        return quoteService.getAllQuotes().stream().map(this::convertToQuoteEntityDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public QuoteEntityDTO getQuoteById(@PathVariable Long id) {
        return convertToQuoteEntityDTO(quoteService.getQuoteById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateQuote(@PathVariable Long id,
                                                  @RequestBody QuoteEntityDTO quoteEntityDTO) {
        quoteService.updateQuote(id, convertToQuoteEntity(quoteEntityDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteQuote(@PathVariable Long id) {
        quoteService.deleteQuote(id);
    }

    @PostMapping("/{id}/upvote")
    public QuoteEntityDTO upvoteQuote(@PathVariable Long id) {
        return convertToQuoteEntityDTO(quoteService.upvoteQuote(id));
    }

    @PostMapping("/{id}/downvote")
    public QuoteEntityDTO downvoteQuote(@PathVariable Long id) {
        return convertToQuoteEntityDTO(quoteService.downvoteQuote(id));
    }

    @GetMapping("/top")
    public List<QuoteEntityDTO> getTopQuotes(@RequestParam(name = "limit", defaultValue = "10") Integer limit) {
        return quoteService.getAllQuotes(limit, Sort.Direction.DESC).stream().map(this::convertToQuoteEntityDTO)
                .toList();
    }

    @GetMapping("/worse")
    public List<QuoteEntityDTO> getWorseQuotes(@RequestParam(name = "limit", defaultValue = "10") Integer limit) {
        return quoteService.getAllQuotes(limit, Sort.Direction.ASC).stream().map(this::convertToQuoteEntityDTO)
                .toList();
    }

    QuoteEntity convertToQuoteEntity(QuoteEntityDTO quoteEntityDTO) {
        return modelMapper.map(quoteEntityDTO, QuoteEntity.class);
    }

    private QuoteEntityDTO convertToQuoteEntityDTO(QuoteEntity quoteEntity) {
        return modelMapper.map(quoteEntity, QuoteEntityDTO.class);
    }
}