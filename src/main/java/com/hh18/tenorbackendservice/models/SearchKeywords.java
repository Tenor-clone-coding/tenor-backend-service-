package com.hh18.tenorbackendservice.models;

import com.hh18.tenorbackendservice.dto.SearchKeywordsDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class SearchKeywords extends CreatedTimeStamped{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String keyword;

    @Column(nullable = false)
    private LocalDateTime lastAccessed;

    public SearchKeywords(String keyword, LocalDateTime lastAccessed) {
        this.keyword = keyword;
        this.lastAccessed = lastAccessed;
    }

    public void update(SearchKeywordsDto searchKeywordsDto){
        this.keyword = searchKeywordsDto.getKeyword();
        this.lastAccessed = searchKeywordsDto.getLastAccessed();
    }
}
