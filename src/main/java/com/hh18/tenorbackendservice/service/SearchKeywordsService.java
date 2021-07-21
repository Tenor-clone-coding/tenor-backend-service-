package com.hh18.tenorbackendservice.service;

import com.hh18.tenorbackendservice.dto.SearchKeywordsDto;
import com.hh18.tenorbackendservice.models.SearchKeywords;
import com.hh18.tenorbackendservice.repository.SearchKeywordsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchKeywordsService {
    private final SearchKeywordsRepository searchKeywordsRepository;

    @Transactional
    public void save(SearchKeywordsDto searchKeywordsDto){
        String keywords = searchKeywordsDto.getKeyword();
        SearchKeywords searchKeywords = new SearchKeywords(keywords);
        searchKeywordsRepository.save(searchKeywords);
    }

    @Transactional
    public List<SearchKeywords> recentKeywords(){
        List<SearchKeywords> keywords = searchKeywordsRepository.findTop5ByOrderByCreatedAtDesc();
        return keywords;
    }
}
