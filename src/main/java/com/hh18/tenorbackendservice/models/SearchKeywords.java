package com.hh18.tenorbackendservice.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class SearchKeywords extends CreatedStamped{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String keyword;

    public SearchKeywords(String keywords) {
        this.keyword = keywords;
    }
}
