package com.hh18.tenorbackendservice.controller;

import com.hh18.tenorbackendservice.models.File;
import com.hh18.tenorbackendservice.models.SearchKeywords;
import com.hh18.tenorbackendservice.service.SearchKeywordsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://tenor-test1.s3-website.ap-northeast-2.amazonaws.com")
public class SearchController {
    private final SearchKeywordsService searchKeywordsService;

    @GetMapping("api/recentKeywords")
    public List<SearchKeywords> callRecentKeywords() {
        return searchKeywordsService.recentKeywords();
    }
}
