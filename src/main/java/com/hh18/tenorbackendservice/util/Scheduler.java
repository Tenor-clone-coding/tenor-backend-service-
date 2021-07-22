package com.hh18.tenorbackendservice.util;

import com.hh18.tenorbackendservice.dto.SearchKeywordsDto;
import com.hh18.tenorbackendservice.models.SearchKeywords;
import com.hh18.tenorbackendservice.repository.SearchKeywordsRepository;
import com.hh18.tenorbackendservice.service.SearchKeywordsService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class Scheduler {

    private final SearchKeywordsRepository keywordsRepository;
    private final SearchKeywordsService keywordsService;

    // 매 시간마다 업데이트
    @Scheduled(cron = "0 0 * * * *")
    public void updateCount() throws InterruptedException {
        System.out.println("카운트 업데이트 실행");
        List<SearchKeywords> keywordsList = keywordsRepository.findAll();
        for (int i = 0; i < keywordsList.size(); i++) {
            SearchKeywordsDto searchKeywordsDto = new SearchKeywordsDto();
            SearchKeywords p = keywordsList.get(i);
            Long likeCount = p.getLikeCount();
            String keyword = p.getKeyword();
            if (keywordsService.isHot(keyword)) {
                searchKeywordsDto.setLikeCount(likeCount + 1);
            } else {
                searchKeywordsDto.setLikeCount(0L);
            }
            p.update(searchKeywordsDto);
            return;
        }
    }
}