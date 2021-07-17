package com.hh18.tenorbackendservice.dto;

import com.hh18.tenorbackendservice.models.Photo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PhotoDto {
    private Long id;
    private Long fileId;
    private Long user_id;
    private String title;

    public Photo toEntity(){
        Photo build = Photo.builder()
                .id(id)
                .fileId(fileId)
                .user_id(user_id)
                .title(title)
                .build();
        return build;
    }

    @Builder
    public PhotoDto(Long id,Long fileId,Long user_id,String title){
        this.id = id;
        this.fileId = fileId;
        this.user_id = user_id;
        this.title = title;
    }
}
