package com.hh18.tenorbackendservice.service;

import com.hh18.tenorbackendservice.dto.SearchKeywordsDto;
import com.hh18.tenorbackendservice.models.SearchKeywords;
import com.hh18.tenorbackendservice.repository.SearchKeywordsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchKeywordsService {
    private final SearchKeywordsRepository searchKeywordsRepository;

    @Transactional
    public void save(SearchKeywordsDto searchKeywordsDto) {
        String keywords = searchKeywordsDto.getKeyword();
        SearchKeywords searchKeywords = new SearchKeywords(keywords,LocalDateTime.now());
        searchKeywordsRepository.save(searchKeywords);
    }

    @Transactional
    public List<SearchKeywords> recentKeywords() {
        List<SearchKeywords> keywords = searchKeywordsRepository.findTop5ByOrderByLastAccessedDesc();
        return keywords;
    }

    @Transactional
    public Boolean isKeywordExist(String keyword) {
        SearchKeywords searchKeywords = searchKeywordsRepository.findByKeyword(keyword);
        if (searchKeywords == null) {
            return false;
        } else {
            return true;
        }
    }

    @Transactional
    public void updateKeywordSearchHistory(String keyword){
        SearchKeywords searchKeywords = searchKeywordsRepository.findByKeyword(keyword);
        SearchKeywordsDto searchKeywordsDto = new SearchKeywordsDto();
        searchKeywordsDto.setKeyword(keyword);
        searchKeywordsDto.setLastAccessed(LocalDateTime.now());
        searchKeywords.update(searchKeywordsDto);
    }


}
