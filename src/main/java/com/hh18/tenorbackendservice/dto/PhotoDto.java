package com.hh18.tenorbackendservice.dto;

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
}
