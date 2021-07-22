package com.hh18.tenorbackendservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SearchKeywordsDto {
    private String keyword;
    private LocalDateTime lastAccessed;
    // 여기는 그냥 likeCount를 추가
    private Long likeCount;
}
