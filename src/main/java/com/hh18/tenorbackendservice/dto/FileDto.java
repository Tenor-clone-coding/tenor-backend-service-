package com.hh18.tenorbackendservice.dto;

import com.hh18.tenorbackendservice.models.File;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FileDto {
    private Long id;
    private String originFname;
    private String fname;
    private String fPath;
    private String title;

    public File toEntity(){
        File build = File.builder()
                .originFname(originFname)
                .fname(fname)
                .fPath(fPath)
                .title(title)
                .build();
        return build;
    }

    @Builder
    public FileDto(Long id,String originFname,String fname,String fPath,String title){
        this.id = id;
        this.originFname = originFname;
        this.fname = fname;
        this.fPath = fPath;
        this.title = title;
    }
}
