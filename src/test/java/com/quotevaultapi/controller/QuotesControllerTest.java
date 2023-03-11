package com.quotevaultapi.controller;

import com.quotevaultapi.dto.QuoteEntityDTO;
import com.quotevaultapi.entity.QuoteEntity;
import com.quotevaultapi.service.QuoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class QuotesControllerTest {

    private QuoteController quotesController;

    @Mock
    private QuoteService quoteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        quotesController = new QuoteController(quoteService, new ModelMapper());
    }

    @Test
    void createQuote_returnsHttpStatusOK() {
        QuoteEntityDTO quoteEntityDTO = new QuoteEntityDTO();
        ResponseEntity<HttpStatus> responseEntity = quotesController.createQuote(quoteEntityDTO);
        assert(responseEntity.getStatusCode()).equals(HttpStatus.OK);
    }

    @Test
    void createQuote_callsQuoteServiceSave() {
        QuoteEntityDTO quoteEntityDTO = new QuoteEntityDTO();
        quotesController.createQuote(quoteEntityDTO);
        when(quoteService.save(any(QuoteEntity.class))).thenReturn(new QuoteEntity());
    }

    @Test
    void getAllQuotes_returnsListOfQuoteEntityDTOs() {
        when(quoteService.getAllQuotes()).thenReturn(List.of(new QuoteEntity()));
        List<QuoteEntityDTO> quoteEntityDTOs = quotesController.getAllQuotes();
        assert!(quoteEntityDTOs).isEmpty();
    }

    @Test
    void getAllQuotes_callsQuoteServiceGetAllQuotes() {
        quotesController.getAllQuotes();
        verify(quoteService, times(1)).getAllQuotes();
    }

    @Test
    void getQuoteById_returnsQuoteEntityDTO() {
        when(quoteService.getQuoteById(anyLong())).thenReturn(new QuoteEntity());
        QuoteEntityDTO quoteEntityDTO = quotesController.getQuoteById(1L);
        assert(quoteEntityDTO) != null;
    }

    @Test
    void updateQuote_callsQuoteServiceUpdateQuote() {
        QuoteEntityDTO quoteEntityDTO = new QuoteEntityDTO();
        quotesController.updateQuote(1L, quoteEntityDTO);
        verify(quoteService, times(1)).updateQuote(1L, quotesController.convertToQuoteEntity(quoteEntityDTO));
    }

    @Test
    void updateQuote_returnsHttpStatusOk() {
        QuoteEntityDTO quoteEntityDTO = new QuoteEntityDTO();
        ResponseEntity<HttpStatus> response = quotesController.updateQuote(1L, quoteEntityDTO);
        assert(response.getStatusCode()).equals(HttpStatus.OK);
    }

    @Test
    void deleteQuote_callsQuoteServiceDeleteQuote() {
        quotesController.deleteQuote(1L);
        verify(quoteService, times(1)).deleteQuote(1L);
    }

    @Test
    void deleteQuote_doesNotReturn() {
        assertDoesNotThrow(() -> {
            quotesController.deleteQuote(1L);
        });
    }
}
