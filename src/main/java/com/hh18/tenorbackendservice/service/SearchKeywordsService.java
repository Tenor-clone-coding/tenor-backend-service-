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

    // isHot 지금 검색하면 24시간 내에 포함될 때 true 아니면 false를 반환
    @Transactional
    public Boolean isHot(String keyword) {
        SearchKeywords searchKeywords = searchKeywordsRepository.findByKeyword(keyword);
        LocalDateTime lastHot = searchKeywords.getLastAccessed().plusHours(1);
        if (LocalDateTime.now().isBefore(lastHot)) {
            return true;
        } else {
            return false;
        }
    }

    // 초기화를 자동으로 해주려고 했는데 고민중
//    @Transactional
//    public void resetLikeCnt() {
//
//    }

    /* 
    여기를 잘못 건드린것 같은데 Lonc cnt로 현재 검색 횟수를 반환 (이름은 실수로 likeCount를 했는데 trendCount 등으로 바꾸면 좋을것 같아요)
    그 다음에 set으로 ++를 해줬는데 실제로는 isHot 여부에 따라 조건으로 올라가야 하는데 그냥 올려둔상태로 건드리질 않았습니다.
    */
    @Transactional
    public void updateKeywordSearchHistory(String keyword){
        SearchKeywords searchKeywords = searchKeywordsRepository.findByKeyword(keyword);
        SearchKeywordsDto searchKeywordsDto = new SearchKeywordsDto();
        searchKeywordsDto.setKeyword(keyword);
        searchKeywordsDto.setLastAccessed(LocalDateTime.now());
        Long cnt = searchKeywords.getLikeCount();
        searchKeywords.setLikeCount(++cnt);
        searchKeywords.update(searchKeywordsDto);
    }

    /* 
    여기서는 24시간 내로 검색량이 없었다면 0으로 초기화 하는 부분을 만들어두려고 했는데 스케쥴러 코드에서 써주려다가 정신이 없어서 일단 스킵했어요
    */
    @Transactional
    public void resetTrendKeywordSearchHistory(String keyword) {
        SearchKeywords searchKeywords = searchKeywordsRepository.findByKeyword(keyword);
        SearchKeywordsDto searchKeywordsDto = new SearchKeywordsDto();
        searchKeywordsDto.setKeyword(keyword);
        searchKeywordsDto.setLastAccessed(LocalDateTime.now());
        searchKeywords.setLikeCount(0L);
        searchKeywords.update(searchKeywordsDto);
    }


}
