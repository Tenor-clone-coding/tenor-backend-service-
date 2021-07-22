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

    @Column(nullable = false)
    private Long likeCount;

    /* 
    생성될 때 0을 기본값으로 넣어주는 함수로 default값을 쓰면 insert가 안된상태로 null값인 경우가 있다고해서 PerPersist를 사용했습니다.
    참조: https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=tok0419&logNo=221599220917
    */

    @PrePersist
    public void initialLikeCnt() {
        this.likeCount = 0L;
    }

    public SearchKeywords(String keyword, LocalDateTime lastAccessed) {
        this.keyword = keyword;
        this.lastAccessed = lastAccessed;
        this.likeCount++;
    }

    public void update(SearchKeywordsDto searchKeywordsDto){
        this.keyword = searchKeywordsDto.getKeyword();
        this.lastAccessed = searchKeywordsDto.getLastAccessed();
    }
}
