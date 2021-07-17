package com.hh18.tenorbackendservice.controller;


import com.hh18.tenorbackendservice.dto.PhotoDto;
import com.hh18.tenorbackendservice.models.Photo;
import com.hh18.tenorbackendservice.repository.PhotoRepository;
import com.hh18.tenorbackendservice.service.FileService;
import com.hh18.tenorbackendservice.service.PhotoService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;



import java.util.List;


@RestController
@RequiredArgsConstructor
public class PhotoController {
    private final PhotoService photoService;
    private final FileService fileService;
    private final PhotoRepository photoRepository;

    @GetMapping("api/photos")
    public List<Photo> readPhotoList(PhotoDto requestDto){
        return photoRepository.findAllByOrderByCreatedAtDesc();
    }

}
