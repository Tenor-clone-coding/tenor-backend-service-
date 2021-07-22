package com.hh18.tenorbackendservice.controller;

import com.hh18.tenorbackendservice.dto.PhotoDto;
import com.hh18.tenorbackendservice.models.File;
import com.hh18.tenorbackendservice.models.SearchKeywords;
import com.hh18.tenorbackendservice.repository.SearchKeywordsRepository;
import com.hh18.tenorbackendservice.service.SearchKeywordsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SearchController {
    private final SearchKeywordsService searchKeywordsService;
    private final SearchKeywordsRepository searchKeywordsRepository;

    @GetMapping("api/recentKeywords")
    public List<SearchKeywords> callRecentKeywords() {
        return searchKeywordsService.recentKeywords();
    }

    /*
    여기서는 hot을 검색하면 top5만 나오게 미리 짜두신 리포지토리 JPA 사용했는데 나중에 변경해도 될거같습니다.
    */
    @GetMapping("api/search/hot")
    public List<SearchKeywords> trendingKeywords() {
        return searchKeywordsRepository.findTop5ByOrderByLastAccessedDesc();
    }

}
