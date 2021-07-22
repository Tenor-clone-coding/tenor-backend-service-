package com.hh18.tenorbackendservice.repository;

import com.hh18.tenorbackendservice.models.SearchKeywords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchKeywordsRepository extends JpaRepository<SearchKeywords,Long> {
    List<SearchKeywords> findTop5ByOrderByLastAccessedDesc();
    SearchKeywords findByKeyword(String keyword);
}
