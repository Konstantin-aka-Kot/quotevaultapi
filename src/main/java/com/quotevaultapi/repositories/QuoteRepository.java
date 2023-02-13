package com.quotevaultapi.repositories;

import com.quotevaultapi.models.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {
    List<Quote> findTop10ByOrderByVotesDesc();

    List<Quote> findTop10ByOrderByScoreAsc();
}
