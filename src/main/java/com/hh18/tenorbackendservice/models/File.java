package com.hh18.tenorbackendservice.models;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File extends TimeStamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String originFname;

    @Column(nullable = false)
    private String fname;

    @Column(nullable = false)
    private String fPath;

    @Column(nullable = false)
    private String title;

    @Builder
    public File(Long id,String originFname,String fname,String fPath,String title){
        this.id = id;
        this.originFname = originFname;
        this.fname = fname;
        this.fPath = fPath;
        this.title = title;
    }

}
