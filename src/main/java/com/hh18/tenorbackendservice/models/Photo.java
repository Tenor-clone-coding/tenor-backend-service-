package com.hh18.tenorbackendservice.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Photo extends TimeStamped{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private Long fileId;

    @Column(nullable = false)
    private Long user_id;

    @Column(nullable = false)
    private String title;

    @Builder
    public Photo(Long id,Long fileId,Long user_id,String title){
        this.id = id;
        this.fileId = fileId;
        this.user_id = user_id;
        this.title = title;
    }
}
