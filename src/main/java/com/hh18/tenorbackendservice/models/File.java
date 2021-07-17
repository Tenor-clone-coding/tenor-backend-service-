package com.hh18.tenorbackendservice.models;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String originFname;

    @Column(nullable = false)
    private String fname;

    @Column(nullable = false)
    private String fPath;

    @Builder
    public File(Long id,String originFname,String fname,String fPath){
        this.id = id;
        this.originFname = originFname;
        this.fname = fname;
        this.fPath = fPath;
    }

}
