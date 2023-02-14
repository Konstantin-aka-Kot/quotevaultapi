package com.quotevaultapi.repositories;

import com.quotevaultapi.entities.QuoteEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<QuoteEntity, Long> {
}
